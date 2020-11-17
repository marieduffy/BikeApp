package com.electro.bikeapp.utils

import groovy.util.logging.Slf4j
import org.springframework.security.crypto.password.PasswordEncoder

@Slf4j
class StringEncryption implements PasswordEncoder {

    @Override
    String encode(CharSequence inputString) {
        //log.info(inputString.toString())
        int inputStringLen
        int sqrtoflen
        int twoDimensionalArraySize
        int index = 0
        char underscore = '_'

        inputStringLen = inputString.length()

        // Take square root or string length
        sqrtoflen = (int)Math.sqrt(inputStringLen)

        // Find the closest perfect square to the size of the input string
        if (sqrtoflen * sqrtoflen == inputStringLen) {
            twoDimensionalArraySize = sqrtoflen
        }
        else {
            twoDimensionalArraySize = sqrtoflen + 1
        }

        // Make string length a perfect square by appending "_" to the end
        for (int i = 0; i < twoDimensionalArraySize * twoDimensionalArraySize - inputStringLen; i++) {
            inputString += underscore
        }

        // Initialize the 1D arrays, copy string into 1D tempArr
        char[] tempArr = inputString
        char[] OneDimensionalArray = new char[twoDimensionalArraySize * twoDimensionalArraySize]

        // Replace (space) with _
        for (int i = 0; i < inputString.length(); i++) {
            if (tempArr[i] == ' ' as char) {
                tempArr[i] = underscore
            }
            //log.info(tempArr[i] as String)
        }

        // Initializes both 2D arrays
        char[][] initialArr = new char[twoDimensionalArraySize][twoDimensionalArraySize]
        char[][] finalArr = new char[twoDimensionalArraySize][twoDimensionalArraySize]

        // Stores 1D tempArr into the 2D initialArr
        for (int i = 0; i < twoDimensionalArraySize; i++) {
            for (int j = 0; j < twoDimensionalArraySize; j++) {
                initialArr[i][j] = tempArr[i * twoDimensionalArraySize + j]
                //log.info("2D Array[" + i + "][" + j + "] = " + initialArr[i][j] + "\n")
            }
        }

        // Swaps the rows and Cols of the initialArr into the finalArr
        for (int i = 0; i < twoDimensionalArraySize; i++) {
            for (int j = 0; j < twoDimensionalArraySize; j++) {
                finalArr[i][j] = initialArr[j][i]
                //log.info("2D Array[" + i + "][" + j + "] = " + finalArr[i][j] + "\n")
            }
        }

        // Converts the 2D finalArr back into a 1D array
        for (int y = 0; y < twoDimensionalArraySize; y++) {
            for (int x = 0; x < twoDimensionalArraySize; x++) {
                OneDimensionalArray[index] = finalArr[y][x]
                index++
            }
        }

        // Concatenates the 1D array back into a string
        String answer = new String(OneDimensionalArray)

        // Answer has been encrypted by one stage at this point

        // Cast string to char array
        char[] rot13 = answer.toCharArray()
        char[] asciiArray = new char[answer.length()]

        // Convert char array to array of ascii values
        for (int i = 0; i < rot13.length; i++) {
            asciiArray[i] = (int)rot13[i]
        }

        // Shift each ascii value by 13 (symmetrical)
        int thirteen = 13
        int twentysix = 26
        for (int i = 0; i < rot13.length; i++) {
            // Char a .. z
            if (asciiArray[i] > 96 && asciiArray[i] < 123) {
                // Add 13
                asciiArray[i] = asciiArray[i] + thirteen
                // After we added, if you are no longer between a and z
                if (asciiArray[i] > 122) {
                    // Wrap char back around to a
                    asciiArray[i] = asciiArray[i] - twentysix
                }
            }
            // Char A .. Z
            else if (asciiArray[i] > 64 && asciiArray[i] < 91) {
                // Add 13
                asciiArray[i] = asciiArray[i] + thirteen
                // After we added, if you are no longer between A and Z
                if (asciiArray[i] > 90) {
                    // Wrap char back around to A
                    asciiArray[i] = asciiArray[i] - twentysix
                }
            }
        }
        // Make new string array
        char[] stringArray = new char[answer.length()]
        // Convert ascii values back to character values
        for (int i = 0; i < rot13.length; i++) {
            stringArray[i] = (char)asciiArray[i]
        }
        // Convert char array to string
        String fullyEncryptedPass = stringArray.toString()

        // Prints out encrypted string
        //log.info(fullyEncryptedPass)

        // Return twice encrypted string
        return fullyEncryptedPass
    }

    @Override
    boolean matches(CharSequence rawPassword, String encodedPassword) {
        boolean matches = false

        String checkPassword = encode(rawPassword)

        //log.info("RAW: " + rawPassword.toString())
        //log.info("ENCODED: " + encodedPassword)
        //log.info("CHECK: " + checkPassword)

        if (checkPassword == encodedPassword) {
            matches = true
        }

        return matches
    }

}
