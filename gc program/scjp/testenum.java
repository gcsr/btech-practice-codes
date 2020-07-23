enum animals
{
dog("woof"),cat("meow"),fish("burble");
String sound;
animals(String s)
{
sound=s;
}
}
class testenum{
static animals a;
public static void main(String gcs[])
{
System.out.println(a.dog.sound);
}
}