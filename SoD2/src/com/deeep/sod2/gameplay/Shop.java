package com.deeep.sod2.gameplay;

/*
 * Class : Shop
 * Package : com.deeep.sod2.gameplay
 * Author : Andreas
 * Date : 12-10-13
 */

import com.deeep.sod2.utility.Logger;

public class Shop {

    public static Shop MERCURY = new Shop(0b000001);

    private boolean[] shopData;

    /**
     * @param i data byte must be between 0 and 0b11111111_11111111_11111111_11111111 (4294967295)
     */
    public Shop(int i) {
        /** If i > 4294967295 or i < 0 it's not valid and we cancel*/
        if(i > 0b11_1111 || i < 0) {
            Logger.getInstance().error(this.getClass(), "Overflow! Binary number exceeded 0b11111111_11111111_11111111_11111111 (4294967295)");
            return;
        }
        /** Shop data is initialized with 32 boolean values*/
        shopData = new boolean[32];
        for(int j=31; j>=0; j--){
            /** Here's a touching story...
             *
             * i>>j&1!=0 will tell, whether or not a certain byte is 1
             *  We start by bit shifting. in the first loop j = 31, we
             *  bit shift RIGHT 31 places.
             *  Start ------------- ... ------V
             *          1   2   3   ...   31  32
             *        | 0 | 1 | 1 | ... | 1 | 0 |
             *      End ^---------- ... -------
             *  Next loop j=30 and we bit shift RIGHT 30 places
             *  Start ------------- ... ------V
             *          1   2   3   ...   31  32
             *        | 0 | 1 | 1 | ... | 1 | 0 |
             *          End ^------ ... -------
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
            shopData[31-j]=k;
        }
    }

    public boolean isSpeedPickupForSale(){
        return shopData[0];
    }

    public boolean isHeartPickupForSale(){
        return shopData[1];
    }

    public boolean isBulletPickupForSale(){
        return shopData[2];
    }

    public boolean isCompassPickupForSale(){
        return shopData[3];
    }

}
