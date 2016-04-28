trip.data <- read.csv("D:/BigData/ProjectDataSet/R_Analysis_data/Weather_Holiday_Data1.csv", stringsAsFactors=FALSE)
trip.data
trips_by_day <- trip.data$NumberOfTrips

trips_by_day_timeSeries1 <- ts(trips_by_day, frequency = 7)

trips_by_day_timeSeries1

acf(trips_by_day_timeSeries1)
pacf(trips_by_day_timeSeries1)

#auto regression, integration, moving avg - need orders
new=diff(sqrt(trips_by_day_timeSeries1))
acf(new)
pacf(new)
new1=diff(new)
acf(new1)
pacf(new1)
new2=diff(new1)
acf(new2)
pacf(new2)

new3 = diff(new2)
acf(new3)
pacf(new3)

# Number of trials for getting the best model
# We got a better model in 4th round

#arima(x=trips_by_day_timeSeries1,order=c(0,3,1))
#arima(x=trips_by_day_timeSeries1,order=c(1,3,1))
#arima(x=trips_by_day_timeSeries1,order=c(3,3,1))


#arima(x=trips_by_day_timeSeries1,order=c(0,2,1))
#arima(x=trips_by_day_timeSeries1,order=c(1,2,1))
arima(x=trips_by_day_timeSeries1,order=c(4,2,1))

fit <- arima(trips_by_day_timeSeries1, order = c(4,2,1))

predict(fit, n.ahead = 7)

#[1] 3964.292271 3283.014734 3432.335700 3605.968658 4077.019842 3686.878056
#[7] 3583.430920

###############################################################

acf(lh, type = "covariance")
pacf(lh)

acf(ldeaths)
acf(ldeaths, ci.type = "ma")
acf(ts.union(mdeaths, fdeaths))
ccf(mdeaths, fdeaths, ylab = "cross-correlation")
# (just the cross-correlations)

presidents # contains missing values
acf(presidents, na.action = na.pass)
pacf(presidents, na.action = na.pass)

