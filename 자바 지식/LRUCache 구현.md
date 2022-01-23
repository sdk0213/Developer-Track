* LinkedHashMap 을 통한 구현이 가능하다.
* ```java
  public class LRUCache<K, V> extends LinkedHashMap<K, V>{
    private int capacity;
    public LRUCache(int capacity){
        this.capacity = capacity;
    }
    
    protected boolean removeEldestEntry(Map.Entry<K, V> entry){
        return size() > capactiy;
    }
    
  }
  
* 사용
  ```java
  LRUCache<String, String> lru = new LRUCache(3);
  lru.put("A","A");
  lru.put("B","B");
  lru.put("C","C");
  
  for(Map.Entry<String,String> entry: cache.entrySet()){
       System.out.println(entry.getKey() + " : " + entry.getValue());
  }
  // A
  // B
  // C
  
  lru.get("A");
  for(Map.Entry<String,String> entry: cache.entrySet()){
       System.out.println(entry.getKey() + " : " + entry.getValue());
  }
  
  // B
  // C
  // A
  
  lru.get("D"); // null
  
  lru.put("D");
  for(Map.Entry<String,String> entry: cache.entrySet()){
       System.out.println(entry.getKey() + " : " + entry.getValue());
  }
  
  // C
  // A
  // D
  
