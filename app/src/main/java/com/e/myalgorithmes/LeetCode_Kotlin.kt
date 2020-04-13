package com.e.myalgorithmes

class LeetCode_Kotlin {

    init {
        //numberOfPatterns(1,1)
        //   var arr = {{1, 1, 2, 4}, {-1, -1, 1, 2}, {1, 4, 1, 3}}
        var arr = arrayOf(
                arrayOf(1, 2, 2, 4),
                arrayOf(-1, -1, 1, 2),
                arrayOf(1, 4, 3, 3)
        )
        //    jumpGame(arr)

        //    letterCombinations("23")

        //   generateAbbreviations("word")

        var arr2 = arrayOf(1).toIntArray()
        permute(arr2)


        val strArr: Array<CharArray> = arrayOf(charArrayOf('C', 'A', 'A'),
                charArrayOf('A', 'A', 'A'), charArrayOf('B', 'C', 'D'))
        exist(strArr, "AAB")
    }

    fun isPossible(nums: IntArray): Boolean {
        var sereies = ArrayList<ArrayList<Int>>()

        for (num in nums) {
            var b = false
            for (i in sereies.indices) {
                var size = sereies[i].size
                if (sereies[i][size - 1] + 1 == num && size < 3) {
                    sereies[i].add(num)
                    b = true
                    break
                }
            }

            if (!b) {
                for (i in sereies.indices) {
                    var size = sereies[i].size
                    if (sereies[i][size - 1] + 1 == num) {
                        sereies[i].add(num)
                        b = true
                        break
                    }
                }
            }

            if (!b) {
                var lst = ArrayList<Int>()
                lst.add(num)
                sereies.add(lst)
            }
        }

        for (ser in sereies) {
            if (ser.size < 3) {
                return false
            }
        }

        return true
    }

/////////////////////////////////////////////////////////////////////////////////////////////

    class NumMatrix(private val matrix: Array<IntArray>) {

        val dp = Array(matrix.size) { IntArray(matrix[0].size + 1) }

        init {
            for (i in 0..matrix.size - 1) {
                for (j in 0..matrix[0].size - 1) {
                    dp[i][j + 1] = dp[i][j] + matrix[i][j]
                }
            }
        }


        fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
            var sum = 0
            for (i in row1..row2) {
                sum += dp[i][col2 + 1] - dp[i][col1]
            }
            return sum
        }


/*        fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
            var sum = 0
            for (i in row1..row2) {
                for (j in col1..col2){
                    sum += matrix[i][j]
                }
            }
            return sum
        }*/
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////

    public class Codec() {
        // Encodes a list of strings to a single string.
        val nonAscii = Character.toString(257.toChar())

        fun encode(strs: List<String>): String {
            var ans = ""
            for (s in strs) {
                ans += s + nonAscii
            }
            return ans
        }

        // Decodes a single string to a list of strings.
        fun decode(s: String): List<String> {
            var ans = ArrayList<String>()
            var acc = ""
            for (ch in s) {
                if (ch.toString() == nonAscii) {
                    ans.add(acc)
                    acc = ""
                } else {
                    acc += ch
                }
            }
            return ans
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    fun numberOfPatterns(m: Int, n: Int): Int {
        var used = BooleanArray(9)
        var ans = 0
        for (i in m..n) {
            ans += calcPatterns(i, -1, used)
        }
        return ans
    }

    private fun calcPatterns(count: Int, last: Int, used: BooleanArray): Int {
        var ans = 0
        if (count == 0) {
            return 1
        }
        for (i in used.indices) {
            if (isValid(i, last, used)) {
                used[i] = true
                ans += calcPatterns(count - 1, i, used)
                used[i] = false
            }
        }
        return ans
    }

    private fun isValid(index: Int, last: Int, used: BooleanArray): Boolean {
        if (used[index]) return false
        // first digit of the pattern
        if (last == -1) return true
        // knight moves or adjacent cells (in a row or in a column)
        if ((index + last) % 2 == 1) return true
        // indexes are at both end of the diagonals for example 0,0, and 8,8
        val mid = (index + last) / 2
        if (mid == 4) return used[mid]
        // adjacent cells on diagonal  - for example 0,0 and 1,0 or 2,0 and //1,1
        return if (index % 3 != last % 3 && index / 3 != last / 3) {
            true
        } else used[mid]
        // all other cells which are not adjacent
    }

////////////////////////////////////////////////////////////////////////////////////////////////////


    fun jumpGame(arr: Array<Array<Int>>): Int {
        var mem = Array(arr.size) { IntArray(arr[0].size) }
        for (row in mem) {
            for (i in row.indices) {
                row[i] = -1
            }
        }
        var path = Array(arr.size) { BooleanArray(arr[0].size) }

        return jumpHelper(arr, 0, 0, path, mem)
    }

    private fun jumpHelper(arr: Array<Array<Int>>, i: Int, j: Int, path: Array<BooleanArray>, mem: Array<IntArray>): Int {
        if (i >= arr.size || i < 0 || j >= arr.size || j < 0) {
            return 0
        }
        if (arr[i][j] == -1) {
            return 0
        }
        if (mem[i][j] != -1) {
            return mem[i][j]
        }
        if (path[i][j]) {
            return Integer.MAX_VALUE
        }
        path[i][j] = true
        var s1 = jumpHelper(arr, i + arr[i][j], j, path, mem)
        var s2 = jumpHelper(arr, i - arr[i][j], j, path, mem)
        var s3 = jumpHelper(arr, i, j + arr[i][j], path, mem)
        var s4 = jumpHelper(arr, i, j - arr[i][j], path, mem)
        path[i][j] = false

        val res = 1 + Math.max(s1, Math.max(s2, Math.max(s3, s4)))
        mem[i][j] = res
        return res
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    fun letterCombinations(digits: String): List<String> {

        var ans = ArrayList<String>()

        if (digits.length == 0) {
            return ans
        }

        combinationsHelper(digits, 0, "", ans)
        return ans
    }

    private fun combinationsHelper(digits: String, i: Int, s: String, arr: ArrayList<String>) {

        if (i >= digits.length) {
            arr.add(s)
            return
        }

        var letter0 = (96 + 1 + 3 * ((digits[i].toString().toInt()) - 2)).toChar()
        var letter1 = (96 + 2 + 3 * ((digits[i].toString().toInt()) - 2)).toChar()
        var letter2 = (96 + 3 + 3 * ((digits[i].toString().toInt()) - 2)).toChar()

        combinationsHelper(digits, i + 1, s + letter0, arr)
        combinationsHelper(digits, i + 1, s + letter1, arr)
        combinationsHelper(digits, i + 1, s + letter2, arr)

        if (digits[i].toString() == "7") {
            var letter3 = (96 + 4 + 3 * ((digits[i].toString().toInt()) - 2)).toChar()
            combinationsHelper(digits, i + 1, s + letter3, arr)
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    fun generateAbbreviations(word: String): List<String> {
        var ans = ArrayList<String>()
        abbreviationsHelper(word, 0, "", ans)
        return ans
    }

    private fun abbreviationsHelper(word: String, i: Int, acc: String, arr: ArrayList<String>) {
        if (i == word.length) {
            arr.add(acc)
            return
        }

        var next = word[i]


        var acc2: String = addOneToAcc(acc)
        // var lastInt = (acc.last().toInt()-48)

        abbreviationsHelper(word, i + 1, acc2, arr)
        abbreviationsHelper(word, i + 1, acc + next, arr)
    }

    private fun addOneToAcc(acc: String): String {

        if (acc == "") {
            return "1"
        }
        var ans = ""
        var num1 = 0.0
        var j = acc.length - 1

        while (j >= 0) {
            var lastInt = (acc[j].toInt() - 48)
            if (lastInt in 1..9) {
                num1 = num1 + lastInt * Math.pow(10.0, (acc.length - j - 1).toDouble())
                j--
            } else {
                // j --
                break
            }
        }

        if (num1 > 0) { // number found
            num1++
            ans = acc.substring(0, j + 1) + num1.toInt()
        } else { // letter found
            ans = acc + 1
        }

        return ans
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    fun permute(nums: IntArray): List<List<Int>> {
        var ans = ArrayList<List<Int>>()
        var acc = ArrayList<Int>()
        var numsList = ArrayList(nums.toList())
        permuteHelper(numsList, acc, ans)
        return ans
    }

    private fun permuteHelper(nums: ArrayList<Int>, acc: ArrayList<Int>, arr: ArrayList<List<Int>>) {
        if (nums.isEmpty()) {
            var acc2: ArrayList<Int> = copyAcc(acc)
            arr.add(acc2)
        }

        for (i in nums.indices) {
            var num = nums.removeAt(i)
            acc.add(num)
            //var acc2 = copyAcc(acc)
            permuteHelper(nums, acc, arr)
            acc.removeAt(acc.size - 1)
            nums.add(i, num)
        }
    }

    private fun copyAcc(acc: java.util.ArrayList<Int>): java.util.ArrayList<Int> {
        var ans = ArrayList<Int>()
        for (num in acc) {
            ans.add(num)
        }
        return ans
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    fun exist(board: Array<CharArray>, word: String): Boolean {



        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == word[0]) {
                    var map = HashMap<Int, Boolean>()
                    if (existHelper(board, i, j, 0, word, map)) {
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun existHelper(board: Array<CharArray>, i: Int, j: Int, wordIndex: Int, word: String, map: HashMap<Int, Boolean>): Boolean {

        if (wordIndex == word.length) {
            return true
        }

        if (i < 0 || j < 0 || i == board.size || j == board[0].size) {
            return false
        }

        if (board[i][j] != word[wordIndex]) {
            return false
        }

        if (board[i][j] == '1') {
            return false
        }

        var key = i*200 + j
        var value = map.get(key)

        value?.let{
            return value
        }

        var b0 = false
        var b1 = false
        var b2 = false
        var b3 = false

        var tmp = board[i][j]
        board[i][j] = '1'

        b0 = existHelper(board, i + 1, j, wordIndex + 1, word, map)
        b1 = existHelper(board, i, j + 1, wordIndex + 1, word, map)
        b2 = existHelper(board, i - 1, j, wordIndex + 1, word, map)
        b3 = existHelper(board, i, j - 1, wordIndex + 1, word, map)

        board[i][j] = tmp

        var ans = b0 || b1 || b2 || b3
        map.put(key, ans)

        return ans
    }


}