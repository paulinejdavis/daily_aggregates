# daily_aggregates
<img width="90" alt="arrow" src="https://user-images.githubusercontent.com/19231569/213458967-d77d1ede-cbb8-4cda-8d58-7ac2a1c70503.png">

## Purpose

The aim of this task is to calculate daily stock market summaries from a given historical 
trade log. This includes  tracking open and close prices, highest and lowest prices, daily
traded volume, and a weighted market index for specific stocks.


## Problem statement

Given a historical log of completed trades in a market, we need to extract daily aggregate
values for each traded ticker and calculate a market index based on predefined weights. 
Some stocks may have no trades on certain days, requiring special handling for missing
data.


## Requirements

### Input data
A log file containing trade records in the format:

```
date + time;
company ticker;
price;
number of securities traded
```

### Example
```
2025-01-20 09:00:01;ABC;100;500
2025-01-20 09:00:01;MEGA;200;300
```


### Calculation for each stock per day
* **Open Price:** Price of the first trade of the day
* **Close Price:** Price of the last trade of the day
* **Highest Price:** Max trade price of the day
* **Lowest Price:** Min trade price of the day
* **Daily Traded Volume:** Sum of (price x number of securities traded)
* **Daily values for INDEX:** calculating market index for each day based on he weighte
sum of specific ticker prices


## Market index Calculation

Only specific tickers contribute to the index:
```
ABC: 0.1
MEGA: 0.3
NGL: 0.4
TRX: 0.2
```

* The index is a weighted sum of these tickers' prices at each moment
* If zero trades occur on a given day, the index should use the last
available value.

## Handling Missing Trades
```
Open price: ‘N/A’
Close price: ‘N/A’
Highest price: ‘N/A’
Lowest price: ‘N/A’
Daily traded volume: 0

```
* The market index should retain the last known value from a previous day.


## Usage Example

### Given Input:
—--------------------------------------------------------------------------
```
Market historical log sample:
date+time;company ticker;price;number of securities traded
2025-01-20 09:00:01;ABC;100;500
2025-01-20 09:00:01;MEGA;200;300
2025-01-20 09:00:01;NGL;150;400
2023-01-20 09:00:01;TRX;250;200
2025-01-20 09:20:05;ABC;105;600
2025-01-20 09:25:06;MEGA;195;350
2025-01-20 09:30:07;NGL;155;450
2025-01-20 09:35:09;TRX;245;250
2025-01-20 09:40:00;XYZ;300;150
2025-01-20 09:45:08;LMN;400;100
2028-01-20 09:50:07;OPQ;350;200
2025-01-20 09:55:06;RST;450;250
2025-01-21 09:05:05;ABC;110;550
2025-01-21 09:05:05;MEGA;210;320
2025-01-21 09:05:05;NGL;160;420
2025-01-20 09:05:05;TRX;260;220
2025-01-21 09:20:01;ABC;115;620
2025-01-21 09:25:00;MEGA;205;0
2025-01-21 09:30:59;NGL;165;470
2025-01-21 09:35:55;TRX;255;270
2025-01-21 09:40:45;XYZ;310;160
2025-01-21 09:45:00;LMN;410;110
2025-01-31 09:50:23;OPQ;360;210
2025-01-31 09:55:00;RST;460;260
```
—--------------------------------------------------------------------------

### Expected Output:

```
Date: 2025-01-20
ABC   | Open: 100 | Close: 105 | High: 105 | Low: 100 | Volume: (100*500 + 105*600)
MEGA  | Open: 195 | Close: 195 | High: 195 | Low: 195 | Volume: (195*350)
NGL   | Open: 155 | Close: 155 | High: 155 | Low: 155 | Volume: (155*450)
TRX   | Open: 245 | Close: 245 | High: 245 | Low: 245 | Volume: (245*250)
INDEX: 180

Date: 2025-01-21
ABC   | Open: 110 | Close: 110 | High: 110 | Low: 110 | Volume: (110*550)
MEGA  | Open: 210 | Close: 210 | High: 210 | Low: 210 | Volume: (210*320)
NGL   | Open: 160 | Close: 160 | High: 160 | Low: 160 | Volume: (160*420)
TRX   | Open: N/A | Close: N/A | High: N/A | Low: N/A | Volume: 0
INDEX: 187

```
—--------------------------------------------------------------------------

## Edge cases & usage examples:

### No trades on a given day
```
* Ensure missing tickers are marked with N/A and volume 0.

* Market  index should use the last known value.
```

### Timestamps not in order
```
* sort data by date prior to processing to determine open and closing prices.
```
### Duplicate trades in a second
```
* if multiple trades exust at the same time stamp, use the first seen trade for open price

```

### Zero trades for a ticker on some days
```
* Must explicitly set open/close/high low to N/A , volume to 0.
```
### Index calculation on first day
```
* The market index cannot be computed until all required tickers have traded
at least once
```




## How to run:
```

```

## Setup:
```

```

## Dependencies:
```

```



## Next steps - improvements:
```

```
## Useful commands
```

```