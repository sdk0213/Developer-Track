import java.util.*;

class Solution {
    
    //  4종류의 보석 : RUBY, DIA, EMERALD, SAPPHIRE
    //
    public int[] solution(String[] gems) {
        
        Map<String, Integer> map = new HashMap<>();
        
        Queue<int[]> q = new PriorityQueue<>((a,b) -> {
            if( (a[1] - a[0]) > (b[1] - b[0]) ){
                return 1;
            } else if( (a[1] - a[0]) < (b[1] - b[0])){
                return -1;
            } else {
                if(a[1] > b[1]){
                    return 1;
                } else if(a[1] < b[1]){
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for(String gem: gems){
            map.put(gem, 0);
        }
        
        int validSize = map.size();
        
        map.clear();

//        System.out.println(map.toString());

        int e = 0;
        for(int s = 0 ; s < gems.length ; s++){

            while(e < gems.length && map.size() != validSize){
                map.put(gems[e], map.getOrDefault(gems[e], 0)+1);
                e++;
            }

            if (map.size() == validSize) {
                q.offer(new int[]{s+1, e});
            }

            if(map.get(gems[s]) <= 1){
                map.remove(gems[s]);
            } else {
                map.put(gems[s], map.get(gems[s])-1);   
            }

        }

        // System.out.print(Arrays.toString(answer));
        
        return q.poll();

    }

    public boolean isValid(Map<String, Integer> map){
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() <= 0) return false;
        }
        return true;
    }
}
