groupBy() 함수    
===
* ![](img/marblediagram_groupBy.png)
* 특정한 (어떤)기준으로 Observable 각각을 여러 개 Observable으 그룹을 구분한다.
* ```java
  // CommonUtil의 getShape함수
  public static String getShape(String obj) {
    if (obj == null || obj.equals("")) return "NO-SHAPE";
    if (obj.endsWith("-H")) return "HEXAGON";
    if (obj.endsWith("-O")) return "OCTAGON";
    if (obj.endsWith("-R")) return "RECTANGLE";
    if (obj.endsWith("-T")) return "TRIANGLE";
    if (obj.endsWith("-◇")) return "DIAMOND";
    return "BALL";
  
* ```java
  String[] objs = {"6", "4", "2-T", "6-T", "4-T");
  Observable<GroupedObservable<String, String>> source =
    Observable.fromArray(objs).groupBy(CommonUtils::getShape);
    
  source.subscribe(obj -> {
    obj.subscrbie(
      val -> System.out.println("GROUP:" + obj.getKey() + "\t Value:" + val));
  });
  
  // result
  // 2020-11-26 21:24:25.190 22817-22817/com.study.rxandroid I/System.out: GROUP:BALL	 Value:6
  // 2020-11-26 21:24:25.190 22817-22817/com.study.rxandroid I/System.out: GROUP:BALL	 Value:4
  // 2020-11-26 21:24:25.191 22817-22817/com.study.rxandroid I/System.out: GROUP:TRIANGLE	 Value:2-T
  // 2020-11-26 21:24:25.191 22817-22817/com.study.rxandroid I/System.out: GROUP:BALL	 Value:2
  // 2020-11-26 21:24:25.191 22817-22817/com.study.rxandroid I/System.out: GROUP:TRIANGLE	 Value:6-T
  // 2020-11-26 21:24:25.191 22817-22817/com.study.rxandroid I/System.out: GROUP:TRIANGLE	 Value:4-T
