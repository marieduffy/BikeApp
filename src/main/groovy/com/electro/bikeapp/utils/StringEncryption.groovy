package com.electro.bikeapp.utils

import groovy.util.logging.Slf4j
import org.springframework.security.crypto.password.PasswordEncoder

@Slf4j
class StringEncryption implements PasswordEncoder{

    @Override
    String encode(CharSequence inputString) {
        int inputStringLen
        int sqrtoflen
        int twoDimensionalArraySize
        int index = 0

        inputStringLen = inputString.length()

        // Take square root or string length
        sqrtoflen = (int)Math.sqrt(inputStringLen)

        // Find the closest perfect square to the size of the input string
        if (sqrtoflen * sqrtoflen == inputStringLen)

            twoDimensionalArraySize = sqrtoflen

        else twoDimensionalArraySize = sqrtoflen + 1

        // Make string length a perfect square by appending "_" to the end
        for (int i = 0; i < twoDimensionalArraySize * 2; i++)
        {
            inputString += "_"
        }

        // Initialize the 1D arrays, copy string into 1D tempArr
        char[] tempArr = inputString.toCharArray()
        char[] OneDimensionalArray = new char[twoDimensionalArraySize * twoDimensionalArraySize]

        // Replace (space) with _
        for (int i = 0; i < inputString.length(); i++)
        {
            if (tempArr[i] == ' ' as char)
            {
                tempArr[i] = '_'
            }
            //log.info(tempArr[i] as String)
        }

        // Initializes both 2D arrays
        char[][] initialArr = new char[twoDimensionalArraySize][twoDimensionalArraySize]
        char[][] finalArr = new char[twoDimensionalArraySize][twoDimensionalArraySize]

        // Stores 1D tempArr into the 2D initialArr
        for (int i = 0; i < twoDimensionalArraySize; i++)
        {
            for (int j = 0; j < twoDimensionalArraySize; j++)
            {
                initialArr[i][j] = tempArr[i * twoDimensionalArraySize + j]
                //log.info("2D Array[" + i + "][" + j + "] = " + initialArr[i][j] + "\n")
            }
        }

        // Swaps the rows and Cols of the initialArr into the finalArr
        for (int i = 0; i < twoDimensionalArraySize; i++)
        {
            for (int j = 0; j < twoDimensionalArraySize; j++)
            {
                finalArr[i][j] = initialArr[j][i]
                //log.info("2D Array[" + i + "][" + j + "] = " + finalArr[i][j] + "\n")
            }
        }

        // Converts the 2D finalArr back into a 1D array
        for (int y = 0; y < twoDimensionalArraySize; y++)
        {
            for (int x = 0; x < twoDimensionalArraySize; x++)
            {
                OneDimensionalArray[index] = finalArr[y][x]
                index++;
            }
        }

        // If string contains _, its been encrypted. So remove the _
        if (inputString.contains("_"))
        {
            // Replace _ with (space)
            for (int i = 0; i < OneDimensionalArray.length; i++)
            {
                if (OneDimensionalArray[i] == '_' as char)
                {
                    OneDimensionalArray[i] = ' '
                }
                //log.info(OneDimensionalArray[i] as String)
            }
        }

        // Concatenates the 1D array back into a string
        String answer = new String(OneDimensionalArray)


        // Prints out encrypted string
        //log.info(answer)
        return answer
    }

    @Override
    boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false
    }
}
