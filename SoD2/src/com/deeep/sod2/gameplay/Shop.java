package com.deeep.sod2.gameplay;

/*
 * Class : Shop
 * Package : com.deeep.sod2.gameplay
 * Author : Andreas
 * Date : 12-10-13
 */

import com.deeep.sod2.utility.Logger;

public class Shop {

    public boolean[] shopData;

    /**
     * @param i data byte must be between 0 and 0b111111 (1+2+4+8+16+32=63)
     */
    public Shop(int i) {
        /** If i > 63 (0b111111) or is less than 0 it\s not valid and we cancel*/
        if(i > 0b11_1111 || i < 0) {
            Logger.getInstance().error(this.getClass(), "Binary number exceeded 0b111111 (63)");
            return;
        }
        /** Shopdata is initialized with 6 booleans*/
        shopData = new boolean[6];
        for(int j=5; j>=0; j--){
            /** Here's a touching story...
             *
             * i>>j&1!=0 will tell, whether or not a certain byte is 1
             *  We start by bit shifting. in the first loop j = 5, we
             *  bit shift RIGHT 5 places.
             *  Start -----------V
             *         1 2 3 4 5 6
             *        |0|1|1|0|1|0|
             *     End ^----------
             *  Next loop j=4 and we bit shift RIGHT 4 places
             *  Start -----------V
             *         1 2 3 4 5 6
             *        |0|1|1|0|1|0|
             *       End ^--------
             *  etc.
             *  Every time we evaluate this we add the binary AND &
             *  This will result in TRUE if our binary number is 1
             *  and it will result in FALSE if our binary number is 0.
             *
             *  Then we add the resulted boolean to the shopData
             *
             *  ... the end.
             */
            boolean k=(i>>j&1)!=0;
            shopData[5-j]=k;
        }

    }

}
