
import javax.media.j3d.Canvas3D;
import java.awt.GraphicsConfiguration;
import javax.vecmath.Point3f;
import javax.media.j3d.PointArray;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.GraphicsContext3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PointAttributes;
import javax.media.j3d.Transform3D;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.vecmath.Vector3d;








//Define a custom Canvas3D that implements Immediate Mode rendering
//and outputs the FPS achieved.
public class ImmediateCanvas3D1 extends Canvas3D
{
private long m_nRender = 0;
private long m_StartTime = 0;
private static final int nGridSize = 50;
private static final int m_kReportInterval = 50;
private PointArray m_PointArray = new PointArray( nGridSize *
nGridSize, GeometryArray.COORDINATES );
private Transform3D m_t3d = new Transform3D();
private float m_rot = 0.0f;
ImmediateCanvas3D1(java.awt.GraphicsConfiguration
graphicsConfiguration)
{
super( graphicsConfiguration );
//create the PointArray that we will be rendering
int nPoint = 0;
for( int n = 0; n <nGridSize; n++ )
{
for( int i = 0; i <nGridSize; i++ )
{
Point3f point = new Point3f( n - nGridSize/2,
i - nGridSize/2, 0.0f );
m_PointArray.setCoordinate( nPoint++, point );
}
}
}

public void renderField( int fieldDesc )
{

super.renderField( fieldDesc );
GraphicsContext3D g = getGraphicsContext3D();
//first time initialization
if( m_nRender == 0 )
{
//set the start time
m_StartTime = System.currentTimeMillis();
//add a light to the graphics context
DirectionalLight light = new DirectionalLight( );
light.setEnable( true );
g.addLight( (Light) light );
//create the material for the points
Appearance a = new Appearance();
Material mat = new Material();
mat.setLightingEnable( true );
mat.setAmbientColor( 0.5f, 1.0f, 1.0f );
a.setMaterial( mat );
a.setColoringAttributes(
new ColoringAttributes( 1.0f, 0.5f, 0.5f,
ColoringAttributes.NICEST ) );
//enlarge the points
a.setPointAttributes( new PointAttributes( 4, true ) );
//make the appearance current in the graphics context
g.setAppearance( a );
}
//set the current transformation for the graphics context
g.setModelTransform( m_t3d );
//finally render the PointArray
g.draw( m_PointArray );
//calculate and display the frames per second for the
//immediate mode rendering of the PointArray
m_nRender++;
if( (m_nRender % m_kReportInterval ) == 0 )
{
float fps = (float) 1000.0f /
((System.currentTimeMillis() - m_StartTime) /
(float) m_kReportInterval);
System.out.println( "FPS:\t" + fps );
m_StartTime = System.currentTimeMillis();
}
}

public void preRender()
{
super.preRender();
//update the model transformation to rotate the PointArray about
//the Y axis
m_rot += 0.1;
m_t3d.rotY( m_rot );
//move the transform back so we can see the points
m_t3d.setTranslation( new Vector3d( 0.0, 0.0, -20.0 ) );
//scale the transformation down so we can see all of the points
m_t3d.setScale( 0.3 );
//force a paint (will call renderField)
paint( getGraphics() );
}
}

