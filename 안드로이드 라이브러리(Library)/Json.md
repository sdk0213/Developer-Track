# Json
---
### Json Form
* ```json
  {"members": [
    {
      "name": "Molecule Man",
      "age": 29,
      "secretIdentity": "Dan Jukes",
      "powers": [
        "Radiation resistance",
        "Turning tiny",
        "Radiation blast"
      ]
    },
    {
      "name": "Madame Uppercut",
      "age": 39,
      "secretIdentity": "Jane Wilson",
      "powers": [
        "Million tonne punch",
        "Damage resistance",
        "Superhuman reflexes"
      ]
    },
    {
      "name": "Eternal Flame",
      "age": 1000000,
      "secretIdentity": "Unknown",
      "powers": [
        "Immortality",
        "Heat Immunity",
        "Inferno",
        "Teleportation",
        "Interdimensional travel"
      ]
    }
  ]
* JSONObject
  * '{}' 으로 묶임
* JSONArray
  * '[]' 으로 묶임
---
### JSONObject
* ```kotlin
  var json = "{"
			+ "		code:'1000',"
			+ "		name:'포도'"
			+ "}"
			;
  
  var parser = JSONParser().parse(json)
  
  val jsonObj: JSONObject = parser
  
  println("code : ${jsonObj.get("code")} \n name : ${jsonObj.get("name"))
  
  // result
  // code : 1000
  // name : 포도  
---
### JSONArray
* ```kotlin
  var data1 = JSONObject()
  data1.put("professor", "김교수")
  data1.put("student", "이학생")

  var data2 = JSONObject()
  data2.put("professor", "박교수")
  data2.put("student", "최학생")
		
  var data3 = JSONObject()
  data3.put("professor", "한교수")
  data3.put("student", "황학생")
		
  var arr = JSONArray()
  arr.add(data1)
  arr.add(data2)
  arr.add(data3)
  
  var univ = JSONObject()
  univ.put("univ", arr);
* ```json
  {
  univ: [
       { "professor":"김교수", "student":"이학생" },
       { "professor":"박교수", "student":"최학생" },
       { "professor":"한교수", "student":"황학생" }
       ]
  }
  
  
