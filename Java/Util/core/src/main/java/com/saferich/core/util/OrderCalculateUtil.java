package com.saferich.core.util;

import com.saferich.core.util.CalculateUtil;

/**
 * @author Jason.Zhang
 */
public class OrderCalculateUtil {
    /**
     * 计算管理费用(本金管理费或者收益管理费)
     * 
     * @param money 实际投资金额或者收益
     * @param manageFeeRate 管理费率
     * @return
     */
    public static double getManageFee(double money, double manageFeeRate) {
        return CalculateUtil.multiply(money, manageFeeRate / 100);
    }

    /**
     * 计算认购费
     * 
     * @param investMoney 投资金额
     * @param subscriptionFeeRate 认购费率
     * @return
     */
    public static double getSubscriptionFee(double investMoney, double subscriptionFeeRate) {
        return CalculateUtil.multiply(investMoney, subscriptionFeeRate / 100);
    }

    /**
     * 收益
     * 
     * @param realInvestMoney 实际投资金额
     * @param profitFeeRate 年化收益率
     * @param investTerm 投资周期
     * @return
     */
    public static double getProfit(double realInvestMoney, double profitFeeRate, int investTerm) {
        double yearProfit = CalculateUtil.multiply(realInvestMoney, profitFeeRate / 100);
        double dayProfit = yearProfit / 365;
        return CalculateUtil.round(CalculateUtil.multiply(dayProfit, investTerm), 2);
    }

    /**
     * 汇款中计算实际收益（扣除收益管理费）
     * 
     * @param realInvestMoney
     * @param profitFeeRate
     * @param manageFeeRate
     * @param investTerm
     * @return
     */
    public static double getRealProfit(double realInvestMoney, double profitFeeRate, double manageFeeRate, int investTerm) {
        double profit = getProfit(realInvestMoney, profitFeeRate / 100, investTerm);
        double manageFee = getManageFee(profit, manageFeeRate / 100);
        return CalculateUtil.sub(profit, manageFee);
    }
}
