class Solution {

    // 최단거리는 단순히 좌표계의 x,y 편차인것을 사용하면됨
  
    // 4,3
    val xy = arrayOf(
        arrayOf(1,2,3), 
        arrayOf(4,5,6), 
        arrayOf(7,8,9), 
        arrayOf(10,0,12)
    )

    fun solution(numbers: IntArray, hand: String): String {

        var lastL = 10
        var lastR = 12

        var answer = ""

        // forEach 로 사용해보기
        // (0..numbers.size)
        for(number in numbers) {
            if(number == 1 || number == 4 || number == 7){
                lastL = number
                answer += "L"
            } else if(number == 3 || number == 6 || number == 9){
                lastR = number
                answer += "R"
            } else {
                if(getDistance(lastL, number) < getDistance(lastR, number)){
                    // 왼손이 더 가까울경우
                    lastL = number
                    answer += "L"

                } else if(getDistance(lastL, number) > getDistance(lastR, number)) {
                    // 오른손이 더 가까울경우
                    lastR = number
                    answer += "R"

                } else {
                    // 같을 경우
                    if(hand == "left"){
                        lastL = number
                        answer += "L"

                    } else {
                        lastR = number
                        answer += "R"
                    }
                }
            }

        }

        return answer
    }


    fun getDistance(last: Int, dest: Int): Int {
        // [4,3] 의 배열임
        var lastX = 0
        var lastY = 0
        var destX = 0
        var destY = 0
        for(i in 0..3){
            for(j in 0..2){
                if(last == xy[i][j]){
                    lastX = i
                    lastY = j
                }
                if(dest == xy[i][j]){
                    destX = i
                    destY = j
                }
            }
        }
        return Math.abs(lastX - destX) + Math.abs(lastY - destY)
    }
}
