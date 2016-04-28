trip.data <- read.csv("D:/BigData/ProjectDataSet/R_Analysis_data/Weather_Holiday_Data.csv", stringsAsFactors=FALSE)
trip.data
trips_by_day <- trip.data$NumberOfTrips

trips_by_day_timeSeries <- ts(trips_by_day, frequency = 7)
trips_by_day_timeSeries

acf(trips_by_day_timeSeries)
pacf(trips_by_day_timeSeries)

#auto regression, integration, moving avg - need orders
new=diff(sqrt(trips_by_day_timeSeries))
acf(new)
pacf(new)


new1=diff(new)
acf(new1)
pacf(new1)
new2=diff(new1)
acf(new2)
pacf(new2)


#arima(x=trips_by_day_timeSeries1,order=c(0,3,1))
#arima(x=trips_by_day_timeSeries1,order=c(1,3,1))
#arima(x=trips_by_day_timeSeries1,order=c(3,3,1))


#arima(x=trips_by_day_timeSeries1,order=c(0,2,1))
#arima(x=trips_by_day_timeSeries1,order=c(1,2,1))
arima(x=trips_by_day_timeSeries,order=c(3,2,1))

fit <- arima(trips_by_day_timeSeries, order = c(3,2,1))

predict(fit, n.ahead = 7)


#[1] 3479.877700 3157.774196 3577.971456 4056.204347 3573.171615 3561.795171
#[7] 3694.070624

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

