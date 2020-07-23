
import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class FPGrowth {

    int threshold;
    //fp-tree constructing fileds
    Vector<FPtree> headerTable;
    FPtree fptree;
    //fp-growth
    Map<String, Integer> frequentPatterns;
    
    List<Graph> subGraphs;
    Graph[][] subSubGraphs;
    
    double[] lifts;
    double[] orderedLifts;
    double[] MDCG;
    double[] IMDCG;
    
    
    List <Normal> normals;

    public FPGrowth(File file, int threshold) throws FileNotFoundException {
        this.threshold = threshold;
        fptree(file);
        fpgrowth(fptree, threshold, headerTable);
        print();
        findSubgraphs();
        findRules();
        findMDCGValues();
        findIMDCGValues();
        
        findNormalizedValues();
        drawTables();
    }

    private FPtree conditional_fptree_constructor(Map<String, Integer> conditionalPatternBase, Map<String, Integer> conditionalItemsMaptoFrequencies, int threshold, Vector<FPtree> conditional_headerTable) {
        //FPTree constructing
        //the null node!
        FPtree conditional_fptree = new FPtree("null");
        conditional_fptree.item = null;
        conditional_fptree.root = true;
        //remember our transactions here has oredering and non-frequent items for condition items
        for (String pattern : conditionalPatternBase.keySet()) {
            //adding to tree
            //removing non-frequents and making a vector instead of string
            Vector<String> pattern_vector = new Vector<String>();
            StringTokenizer tokenizer = new StringTokenizer(pattern);
            while (tokenizer.hasMoreTokens()) {
                String item = tokenizer.nextToken();
                if (conditionalItemsMaptoFrequencies.get(item) >= threshold) {
                    pattern_vector.addElement(item);
                }
            }
            //the insert method
            insert(pattern_vector, conditionalPatternBase.get(pattern), conditional_fptree, conditional_headerTable);
            //end of insert method
        }
        return conditional_fptree;
    }

    private void fptree(File file) throws FileNotFoundException {
        //preprocessing fields
        Map<String, Integer> itemsMaptoFrequencies = new HashMap<String, Integer>();
        Scanner input = new Scanner(file);
        List<String> sortedItemsbyFrequencies = new LinkedList<String>();
        Vector<String> itemstoRemove = new Vector<String>();
        preProcessing(file, itemsMaptoFrequencies, input, sortedItemsbyFrequencies, itemstoRemove);
        construct_fpTree(file, itemsMaptoFrequencies, input, sortedItemsbyFrequencies, itemstoRemove);

    }

    private void preProcessing(File file, Map<String, Integer> itemsMaptoFrequencies, Scanner input, List<String> sortedItemsbyFrequencies, Vector<String> itemstoRemove) throws FileNotFoundException {
        while (input.hasNext()) {
            String temp = input.next();
            if (itemsMaptoFrequencies.containsKey(temp)) {
                int count = itemsMaptoFrequencies.get(temp);
                itemsMaptoFrequencies.put(temp, count + 1);
            } else {
                itemsMaptoFrequencies.put(temp, 1);
            }
        }
        input.close();
        //orderiiiiiiiiiiiiiiiiiiiiiiiiiiiing
        //also elimating non-frequents

        //for breakpoint for comparison
        sortedItemsbyFrequencies.add("null");
        itemsMaptoFrequencies.put("null", 0);
        for (String item : itemsMaptoFrequencies.keySet()) {
            int count = itemsMaptoFrequencies.get(item);
            // System.out.println( count );
            int i = 0;
            for (String listItem : sortedItemsbyFrequencies) {
                if (itemsMaptoFrequencies.get(listItem) < count) {
                    sortedItemsbyFrequencies.add(i, item);
                    break;
                }
                i++;
            }
        }
        //removing non-frequents
        //this pichidegi is for concurrency problem in collection iterators
        for (String listItem : sortedItemsbyFrequencies) {
            if (itemsMaptoFrequencies.get(listItem) < threshold) {
                itemstoRemove.add(listItem);
            }
        }
        for (String itemtoRemove : itemstoRemove) {
            sortedItemsbyFrequencies.remove(itemtoRemove);
        }
        //printttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt
        //     for ( String key : list )
        //        System.out.printf( "%-10s%10s\n", key, items.get( key ) );

    }

    private void construct_fpTree(File file, Map<String, Integer> itemsMaptoFrequencies, Scanner input, List<String> sortedItemsbyFrequencies, Vector<String> itemstoRemove) throws FileNotFoundException {
        //HeaderTable Creation
        // first elements use just as pointers
        headerTable = new Vector<FPtree>();
        for (String itemsforTable : sortedItemsbyFrequencies) {
            headerTable.add(new FPtree(itemsforTable));
        }
        //FPTree constructing
        input = new Scanner(file);
        //the null node!
        fptree = new FPtree("null");
        fptree.item = null;
        fptree.root = true;
        //ordering frequent items transaction
        while (input.hasNextLine()) {
            String line = input.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line);
            Vector<String> transactionSortedbyFrequencies = new Vector<String>();
            while (tokenizer.hasMoreTokens()) {
                String item = tokenizer.nextToken();
                if (itemstoRemove.contains(item)) {
                    continue;
                }
                int index = 0;
                for (String vectorString : transactionSortedbyFrequencies) {
                    //some lines of condition is for alphabetically check in equals situatioans
                    if (itemsMaptoFrequencies.get(vectorString) < itemsMaptoFrequencies.get(item) || ((itemsMaptoFrequencies.get(vectorString) == itemsMaptoFrequencies.get(item)) && (vectorString.compareToIgnoreCase(item) < 0 ? true : false))) {
                        transactionSortedbyFrequencies.add(index, item);
                        break;
                    }
                    index++;
                }
                if (!transactionSortedbyFrequencies.contains(item)) {
                    transactionSortedbyFrequencies.add(item);
                }
            }
            //printing transactionSortedbyFrequencies
            /*
            for (String vectorString : transactionSortedbyFrequencies) {
            System.out.printf("%-10s%10s ", vectorString, itemsMaptoFrequencies.get(vectorString));
            }
            System.out.println();
             *
             */
            //printing transactionSortedbyFrequencies
            /*
            for (String vectorString : transactionSortedbyFrequencies) {
            System.out.printf("%-10s%10s ", vectorString, itemsMaptoFrequencies.get(vectorString));
            }
            System.out.println();
             *
             */
            //adding to tree
            insert(transactionSortedbyFrequencies, fptree, headerTable);
            transactionSortedbyFrequencies.clear();
        }
        //headertable reverse ordering
        //first calculating item frequencies in tree
        for (FPtree item : headerTable) {
            int count = 0;
            FPtree itemtemp = item;
            while (itemtemp.next != null) {
                itemtemp = itemtemp.next;
                count += itemtemp.count;
            }
            item.count = count;
        }
        Comparator c = new frequencyComparitorinHeaderTable();
        Collections.sort(headerTable, c);
        input.close();
    }

    void insert(Vector<String> transactionSortedbyFrequencies, FPtree fptree, Vector<FPtree> headerTable) {
        if (transactionSortedbyFrequencies.isEmpty()) {
            return;
        }
        String itemtoAddtotree = transactionSortedbyFrequencies.firstElement();
        FPtree newNode = null;
        boolean ifisdone = false;
        for (FPtree child : fptree.children) {
            if (child.item.equals(itemtoAddtotree)) {
                newNode = child;
                child.count++;
                ifisdone = true;
                break;
            }
        }
        if (!ifisdone) {
            newNode = new FPtree(itemtoAddtotree);
            newNode.count = 1;
            newNode.parent = fptree;
            fptree.children.add(newNode);
            for (FPtree headerPointer : headerTable) {
                if (headerPointer.item.equals(itemtoAddtotree)) {
                    while (headerPointer.next != null) {
                        headerPointer = headerPointer.next;
                    }
                    headerPointer.next = newNode;
                }
            }
        }
        transactionSortedbyFrequencies.remove(0);
        insert(transactionSortedbyFrequencies, newNode, headerTable);
    }

    private void fpgrowth(FPtree fptree, int threshold, Vector<FPtree> headerTable) {
        frequentPatterns = new HashMap<String, Integer>();
        FPgrowth(fptree, null, threshold, headerTable, frequentPatterns);
        int i = 0;
    }

    void FPgrowth(FPtree fptree, String base, int threshold, Vector<FPtree> headerTable, Map<String, Integer> frequentPatterns) {
        for (FPtree iteminTree : headerTable) {
            String currentPattern = (base != null ? base : "") + (base != null ? " " : "") + iteminTree.item;
            int supportofCurrentPattern = 0;
            Map<String, Integer> conditionalPatternBase = new HashMap<String, Integer>();
            while (iteminTree.next != null) {
                iteminTree = iteminTree.next;
                supportofCurrentPattern += iteminTree.count;
                String conditionalPattern = null;
                FPtree conditionalItem = iteminTree.parent;

                while (!conditionalItem.isRoot()) {
                    conditionalPattern = conditionalItem.item + " " + (conditionalPattern != null ? conditionalPattern : "");
                    conditionalItem = conditionalItem.parent;
                }
                if (conditionalPattern != null) {
                    conditionalPatternBase.put(conditionalPattern, iteminTree.count);
                }
            }
            frequentPatterns.put(currentPattern, supportofCurrentPattern);
            //counting frequencies of single items in conditional pattern-base
            Map<String, Integer> conditionalItemsMaptoFrequencies = new HashMap<String, Integer>();
            for (String conditionalPattern : conditionalPatternBase.keySet()) {
                StringTokenizer tokenizer = new StringTokenizer(conditionalPattern);
                while (tokenizer.hasMoreTokens()) {
                    String item = tokenizer.nextToken();
                    if (conditionalItemsMaptoFrequencies.containsKey(item)) {
                        int count = conditionalItemsMaptoFrequencies.get(item);
                        count += conditionalPatternBase.get(conditionalPattern);
                        conditionalItemsMaptoFrequencies.put(item, count);
                    } else {
                        conditionalItemsMaptoFrequencies.put(item, conditionalPatternBase.get(conditionalPattern));
                    }
                }
            }
            //conditional fptree
            //HeaderTable Creation
            // first elements are being used just as pointers
            // non conditional frequents also will be removed
            Vector<FPtree> conditional_headerTable = new Vector<FPtree>();
            for (String itemsforTable : conditionalItemsMaptoFrequencies.keySet()) {
                int count = conditionalItemsMaptoFrequencies.get(itemsforTable);
                if (count < threshold) {
                    continue;
                }
                FPtree f = new FPtree(itemsforTable);
                f.count = count;
                conditional_headerTable.add(f);
            }
            FPtree conditional_fptree = conditional_fptree_constructor(conditionalPatternBase, conditionalItemsMaptoFrequencies, threshold, conditional_headerTable);
            //headertable reverse ordering
            Collections.sort(conditional_headerTable, new frequencyComparitorinHeaderTable());
            //
            if (!conditional_fptree.children.isEmpty()) {
                FPgrowth(conditional_fptree, currentPattern, threshold, conditional_headerTable, frequentPatterns);
            }
        }
    }

    private void insert(Vector<String> pattern_vector, int count_of_pattern, FPtree conditional_fptree, Vector<FPtree> conditional_headerTable) {
        if (pattern_vector.isEmpty()) {
            return;
        }
        String itemtoAddtotree = pattern_vector.firstElement();
        FPtree newNode = null;
        boolean ifisdone = false;
        for (FPtree child : conditional_fptree.children) {
            if (child.item.equals(itemtoAddtotree)) {
                newNode = child;
                child.count += count_of_pattern;
                ifisdone = true;
                break;
            }
        }
        if (!ifisdone) {
            for (FPtree headerPointer : conditional_headerTable) {
                //this if also gurantees removing og non frequets
                if (headerPointer.item.equals(itemtoAddtotree)) {
                    newNode = new FPtree(itemtoAddtotree);
                    newNode.count = count_of_pattern;
                    newNode.parent = conditional_fptree;
                    conditional_fptree.children.add(newNode);
                    while (headerPointer.next != null) {
                        headerPointer = headerPointer.next;
                    }
                    headerPointer.next = newNode;
                }
            }
        }
        pattern_vector.remove(0);
        insert(pattern_vector, count_of_pattern, newNode, conditional_headerTable);
    }

    private void print() throws FileNotFoundException {
        /*
        Vector<String> sortedItems = new Vector<String>();
        sortedItems.add("null");
        frequentPatterns.put("null", 0);
        for (String item : frequentPatterns.keySet()) {
            int count = frequentPatterns.get(item);

            int i = 0;
            for (String listItem : sortedItems) {
                if (frequentPatterns.get(listItem) < count) {
                    sortedItems.add(i, item);
                    break;
                }
                i++;
            }
        }
         * 
         */
    	
    	//Formatter output = new Formatter("a.out");
    	
    //s	for(int i)
    	
    	
    	
        for (String frequentPattern : frequentPatterns.keySet()) {
           System.out.println(frequentPattern);
           System.out.println(frequentPatterns.get(frequentPattern));          
           
        	//output.format("%s\t%d\n", frequentPattern,frequentPatterns.get(frequentPattern));
        }
    	
    	
        /*Formatter output = new Formatter("a.out");
        for (String frequentPattern : frequentPatterns.keySet()) {
            output.format("%s\t%d\n", frequentPattern,frequentPatterns.get(frequentPattern));
        }*/
    }
    
    
    private void findSubgraphs(){    	
    	 Graph gh;
    	 subGraphs=new LinkedList<Graph>();
    	 int i=0;
    	 for (String frequentPattern : frequentPatterns.keySet()) { 
    		 String[] slit=frequentPattern.split(" ");
    		 if(slit.length>2){
    			 gh=new Graph(frequentPattern);
    			 gh.setElements(slit);
    			 gh.setSupport((frequentPatterns.get(frequentPattern)).intValue());
    			 //if(gh==null)
    			 //System.out.println(gh);
    			 subGraphs.add(gh);
    		 }
        }
    }
    
    private void findRules(){
    	
    	lifts=new double[subGraphs.size()];
    	subSubGraphs=new Graph[subGraphs.size()][2];
    	
    	System.out.println("size is "+subGraphs.size());
    	
    	for(int i=0;i<subGraphs.size();i++){
    		subSubGraphs[i]=subGraphs.get(i).getSubGraphs();
    		
    		if(subSubGraphs[i][0]==null)
    		System.out.println(subSubGraphs[i][0]);
    		
    		if(subSubGraphs[i][1]==null)
    		System.out.println(subSubGraphs[i][1]);    		
    		
			//lifts[i]=getLiftValue(subSubGraphs[i],subGraphs.get(i));
    	}	
    	
    	for(int i=0;i<subGraphs.size();i++){
    		lifts[i]=getLiftValue(subSubGraphs[i],subGraphs.get(i));
    	}	
    	
    	
    	
    	/*for(double x:lifts){
    		System.out.print("\t"+x);
    	}*/
    	
    	
    }
    
    private double getLiftValue(Graph[] gg,Graph mainGraph){    	
    	int count1=0;
    	int count2=0;
    	int sum=0;
    	//System.out.println(gg[0]);
    	Graph x;
    	for(int i=0;i<subGraphs.size();i++){
    		x=subGraphs.get(i);    		
    		
    		/*if(x.checkFor(gg[0].elements)){
    			count1+=1;
    		}
    		if(x.checkFor(gg[1].elements)){
    			count2+=1;
    		}*/
    		
    		if(x.checkFor(mainGraph.elements)){
    			sum++;
    		}
    		
    	}
    	
    	for(int i=0;i<subSubGraphs.length;i++){
    		for(int j=0;j<2;j++){
    			//System.out.println(i);
    			//System.out.println(gg[0]);
    			//System.out.println(gg[1]);
    			//System.out.println(subSubGraphs[i][j]);
    			if(subSubGraphs[i][j].checkFor(gg[0].elements))
    				count1++;
    			if(subSubGraphs[i][j].checkFor(gg[1].elements))
    				count2++;
    		}
    	}
    	
    	//System.out.print("\t"+count1);
		//System.out.print("\t"+count2);
		//System.out.print("\t"+sum);
    	
    	return ((sum*1.0)/(count1*count2*1.0));
    }
    
    private void findMDCGValues(){
    	MDCG=new double[subGraphs.size()];
    	
    	for(int i=0;i<MDCG.length;i++){
    		for(int j=1;j<=i;j++){
    			MDCG[i]+=(lifts[j]/(Math.log10(j+1)/Math.log10(2)));
    		}
    	}
    	
    	for(int i=0;i<MDCG.length;i++){
    		MDCG[i]+=(lifts[i]);
    	}
    }
    
    private void findIMDCGValues(){
    	IMDCG=new double[subGraphs.size()];
    	
    	//orderedLifts=lifts;
    	
    	double temp=0;
    	int length=lifts.length;
    	
    	
    	orderedLifts=new double[length];
    	
    	for(int i=0;i<(length);i++){    		
			orderedLifts[i]=lifts[i];
		}
    	
    	Arrays.sort(orderedLifts);

    	for(int i=0;i<(length/2);i++){    		
			temp=orderedLifts[i];
			orderedLifts[i]=orderedLifts[length-i-1];
			orderedLifts[length-i-1]=temp;
		}

	
    	for(int i=0;i<MDCG.length;i++){
    		for(int j=1;j<=i;j++){
    			IMDCG[i]+=(orderedLifts[j]/(Math.log10(j+1)/Math.log10(2)));
    		}
    	}
    	
    	for(int i=0;i<MDCG.length;i++){
    		IMDCG[i]+=(orderedLifts[i]);
    	}    	
    }
    
    private void findNormalizedValues(){ 	
    	
    	normals=new LinkedList<Normal>();
    	for(int i=0;i<lifts.length;i++){
    		normals.add(new Normal("R"+(i+1), (MDCG[i]/IMDCG[i])));
    	}
    	
    	//System.out.println(normals.get(0).str);
    }
    
    public void drawTables(){
    	
    	/*for(int i=0;i<lifts.length;i++){
    		System.out.print(lifts[i]+"\t"+MDCG[i]+"\t");
    	}*/
    	
    	String[] sp,sp1,sp2,sp3,sp0,sp01;
    	ResultsTable rs,rs1;
    	
    	
    	sp0=new String[]{"Frequent Item","Support"};
    	FequentItems fI=new FequentItems(subGraphs,sp0,"Frequent Item Set");
    	fI.setSize(800,600);
    	fI.setVisible(true);
    	
    	sp01=new String[]{"R1","R2"};
    	SubItems SI=new SubItems(subSubGraphs,sp01,"Rules Generated");
    	SI.setSize(800,600);
    	SI.setVisible(true);
    	
    	
    	
    	
    	sp=new String[]{"I","Log I","Rule","Lift","Lift/LogI","MDCG"};
    	rs=new ResultsTable(lifts,MDCG,"MDCG values",sp);
    	rs.setSize(800,600);
    	rs.setVisible(true);
    	
    	sp1=new String[]{"I","Log I","Rule","Ordered Lift","Lift/LogI","IMDCG"};
    	rs1=new ResultsTable(orderedLifts,IMDCG,"IMDCG values",sp1);
    	rs1.setSize(800,600);
    	rs1.setVisible(true);
    	
    	NormalTable nt1,nt2;
    	
    	sp2=new String[]{"Rule", "MDCG/IMDCG"};
    	nt1=new NormalTable(normals,sp2,"Normalized Values");
    	nt1.setSize(300,600);
    	nt1.setVisible(true);
    	
    	List<Normal> orNormals=new LinkedList<Normal>();
    	
    	Iterator iterator=normals.iterator();
    	
    	while(iterator.hasNext()){
    		Normal nr=(Normal)iterator.next();
    		orNormals.add(nr);
    	}
    	
    	
    	Collections.sort(orNormals);
    	
    	sp3=new String[]{"Rule", "Ordered MDCG/IMDCG"};
    	nt2=new NormalTable(orNormals,sp3,"Ordered Normalized Values");
    	nt2.setSize(300,600);
    	nt2.setVisible(true);
    	
    	
    	
    	
    }
    
}
