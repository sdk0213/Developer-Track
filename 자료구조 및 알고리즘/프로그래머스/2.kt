class Solution {
    fun solution(id: String): String {
        var user_id = id.toLowerCase().replace(
            Regex("[^0-9a-z\\.\\-\\_]"), ""
        ).replace(
            Regex("\\.{2,}"), "."
        ).replace(
            Regex("^\\.|\\.$"), ""
        ).replace(
            Regex("^$"), "a"
        )

        if(user_id.length > 15) {
            user_id = user_id.substring(0, 15).replace(Regex("\\.$"), "")
        }

        while(user_id.length < 3) {
            user_id += user_id[user_id.length - 1]
        }
        
        return user_id
    }
}
