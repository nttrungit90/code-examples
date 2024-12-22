package leetcode.misc;

public class StockBuyAndSell {

    /**
     * https://www.geeksforgeeks.org/best-time-to-buy-and-sell-stock/
     *
     */
    public static int maxProfit(int[] prices) {
        int minPriceSofar = Integer.MAX_VALUE;
        int maxProfitSoFar = 0;
        for(int price : prices) {
            if(price < minPriceSofar) {
                // minPrice is updated to the smallest price seen so far.
                minPriceSofar = price;
            } else {
                // maxProfit is updated to the maximum profit that can be achieved up to the current day.
                maxProfitSoFar = Math.max(maxProfitSoFar, price - minPriceSofar);
            }
        }
        return maxProfitSoFar;
    }
}
