val v :String = "Hello"
var v1 :String = " world"
v1 = v + v1;

val num :Int = 1;
val bool :Boolean = true
val character :Char = 'a'
val d :Double = 3.1452689
val df :Float = 3.54827628465f
val bigNum :Long = 4541214
val smallNum :Byte = 127

var num = 4
num match {
  case 1 => print("1 yes")
  case 2=> print("2 yes")
  case 3 => print("3 yes")
  case _ => print("default with _")
}

for(x <- 1 to 4){
  println(x*x)
}

{val x =10; x+20}

var f1 = 0
var f2 = 1
print(f1 + "," + f2)

for(x <- 1 to 10){
  var tmp = f1 + f2;
  f1 = f2;
  f2 = tmp;
  print(f2 + ",");
}

//Functions example
//format
// def <func name>(param name : type ....) : return type = {}


def squareIt(x :Int) : Int = {
  x * x
}

def cubeIt(x :Int) : Int = {
  x * x * x
}

def transformInt(x :Int, f :Int => Int) : Int = {
  f(x)
}
print(squareIt(10))

print(cubeIt(10))

print(transformInt(2, cubeIt))


//lambda function or anonymous functions or functions literals
print(transformInt(2, x => x*x*10))

print(transformInt(2, x => {val y=x*3; y+y}))

print(transformInt(2, x => x*x*10))

def stringConverter(s :String, converter : String => String) :String = {
  converter(s)
}

print(stringConverter("Fella", s => s.toUpperCase));

//Data Structures:
/**
 * Tuples
 * Immutable lists
 */
//tuple
val vishInfo = ("Vishvanath", "Shinde", "male")
val vishInfoExtn = (vishInfo, 38, 42.456)
println(vishInfo._3)
println(vishInfoExtn._1)
println(vishInfoExtn._3)
//key value pair
val cityNameImportance = "Mumbai" -> "Financial Hub"
println(cityNameImportance._2)

//List
val ships = List("Titanic" , "Dreams", "Voyager", "Journey", "Pearl", "Cursed Pirate")
print(ships(3))
print(ships.head) //initial element at head
print(ships.tail) //everything but head

//iterating on it
for(x <- ships){
  print(x + " ~ ")
}

//reduce
val numbs = List(1,2,3,4,5)
println(numbs.reduce( (x: Int, y:Int) => x+y))
println(numbs.filter((x :Int) => x != 2)) // filter
println(numbs.filter(_ != 4)) // short hand for filter

//List concatenate
val n = List(11,22,33,44,55)
val flist = n ++ numbs //concatenate list
println(flist.reverse)
println(flist.sorted)

//Map - key val datatypes need to be same
val maps = Map("Anna" -> "girl", "Ashton" -> "Boy", "James" -> "Boy", "Mandy" -> "Girl")
print(maps("Anna"))
print(util.Try(maps("Rubin")).getOrElse("No found"))