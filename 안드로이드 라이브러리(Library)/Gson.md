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
---
### Type Token
* 특정 타입의 클래스 정보를 넘겨서 타입 안전성을 꿰하도록 코드를 작성하는 기법을 TypeToken이라고 한다.
* ```json
  [
  {
    "plantId": "malus-pumila",
    "name": "Apple",
    "description": "An apple is a sweet, edible fruit produced by an apple tree (Malus pumila). Apple trees are cultivated worldwide, and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe, and were brought to North America by European colonists. Apples have religious and mythological significance in many cultures, including Norse, Greek and European Christian traditions.<br><br>Apple trees are large if grown from seed. Generally apple cultivars are propagated by grafting onto rootstocks, which control the size of the resulting tree. There are more than 7,500 known cultivars of apples, resulting in a range of desired characteristics. Different cultivars are bred for various tastes and uses, including cooking, eating raw and cider production. Trees and fruit are prone to a number of fungal, bacterial and pest problems, which can be controlled by a number of organic and non-organic means. In 2010, the fruit's genome was sequenced as part of research on disease control and selective breeding in apple production.<br><br>Worldwide production of apples in 2014 was 84.6 million tonnes, with China accounting for 48% of the total.<br><br>(From <a href=\"https://en.wikipedia.org/wiki/Apple\">Wikipedia</a>)",
    "growZoneNumber": 3,
    "wateringInterval": 30,
    "imageUrl": "https://upload.wikimedia.org/wikipedia/commons/5/55/Apple_orchard_in_Tasmania.jpg"
  },
  {
    "plantId": "beta-vulgaris",
    "name": "Beet",
    "description": "The beetroot is the taproot portion of the beet plant, usually known in North America as the beet and also known as the table beet, garden beet, red beet, or golden beet. It is one of several of the cultivated varieties of Beta vulgaris grown for their edible taproots and their leaves (called beet greens). These varieties have been classified as B. vulgaris subsp. vulgaris Conditiva Group.<br><br>Other than as a food, beets have use as a food colouring and as a medicinal plant. Many beet products are made from other Beta vulgaris varieties, particularly sugar beet.<br><br>(From <a href=\"https://en.wikipedia.org/wiki/Beetroot\">Wikipedia</a>)",
    "growZoneNumber": 2,
    "wateringInterval": 7,
    "imageUrl": "https://upload.wikimedia.org/wikipedia/commons/2/29/Beetroot_jm26647.jpg"
  },
  ...
  ..
  .
* 위와 같은 json이 있다고 하였을때 이를 파싱한다면 List의 형태로 파싱해야한다. 그리고 이를 평소하던데로 Gson().fromJson(jsonReader, SomeType) 형태로 파싱하게 될텐데 이때는 어떻게 해야할까?.. List<SomeType>을 직접넣는것은 당연히 안된다.
* 제네릭을 가지는 클래스의 타입토큰을 얻기위해서는 제네릭 클래스를 정의한 후에 그 제네릭 클래스를 상속받으면 런타임시에는 제네릭 정보를 가져올 수 있다.
* 코드가 지저분해지니 간단히 익명클래스로 생성하면된다. 
* ```java
  String jsons = "[{\"username\" : \"wonwoo\", \"password\" : \"test\"},{\"username\" : \"seungwoo\", \"password\" : \"test1\"}]";
  List<Account> accounts = gson.fromJson(jsons, new TypeToken<List<Account>>() {}.getType());
  System.out.println(accounts);
  
  
  
