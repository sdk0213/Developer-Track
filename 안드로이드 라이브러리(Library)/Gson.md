# Gson
---
### Gson 이란?
* Json -> Gson -> Java
* Java -> Gson -> Json
* Java의 객체를 Json 사이에서 직렬화, 역직렬화 시켜주는 라이브러리
---
### Java <-> Gson <-> Json
##### data class
* 기본적으로 직렬화, 역직렬화를 쉽게 해주며 Java 객체 안 다른 객체(여기서는 Sex) 까지 변환해준다.
* ```kotlin
  data class Student(
      private var name: String
      private var age: Int
      private var sex: Sex
  )
  
  data class Sex(
      private var sex: String = "Man"
  )
      
* ```kotlin
  val json: String = "{'name' : 'dksung', 'age' :29}"
  val java: Student = Student("dksung", "29")
  
  val gson: Gson = GsonBuilder().create()
  val fromJson: Student = gson.fromJson(json, Student::class.java)
  val toJava: String = gson.toJson(java)
  
  println("name : $[fromJson.name} , age : ${fromJson.age}")
  println(toJava)
  
  // result
  // name : dksung , age : 29
  // {"name" : "dksung", "age" :29, "pencil" :{"sex" : "Man"}}
