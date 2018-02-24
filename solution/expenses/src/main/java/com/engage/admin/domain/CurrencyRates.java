package com.engage.admin.domain;

public class CurrencyRates
{
    private String base;

    private Rate rates;

    private String date;


    public Float getRate(Currencies currencies) {
        Float conversionrate = 0f;
        switch (currencies) {
            case EUR:
                conversionrate = new Float(rates.getEUR());
                break;
            case GBP:
                conversionrate = new Float(rates.getGBP());
                break;
            default:
                conversionrate = new Float(rates.getEUR());
                break;
        }

        return conversionrate;
    }

    public String getBase ()
    {
        return base;
    }

    public void setBase (String base)
    {
        this.base = base;
    }

    public Rate getRates ()
    {
        return rates;
    }

    public void setRates (Rate rates)
    {
        this.rates = rates;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "CurrencyRates [base = "+base+", rates = "+rates+", date = "+date+"]";
    }
}