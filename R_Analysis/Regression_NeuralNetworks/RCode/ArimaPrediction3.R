trip.data <- read.csv("D:/BigData/ProjectDataSet/R_Analysis_data/Weather_Holiday_Data3.csv", stringsAsFactors=FALSE)
trip.data
trips_by_day <- trip.data$NumberOfTrips

trips_by_day_timeSeries3 <- ts(trips_by_day, frequency = 7)
trips_by_day_timeSeries3

acf(trips_by_day_timeSeries3)
pacf(trips_by_day_timeSeries3)

#auto regression, integration, moving avg - need orders
new=diff(sqrt(trips_by_day_timeSeries3))
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
arima(x=trips_by_day_timeSeries3,order=c(3,2,1))

fit <- arima(trips_by_day_timeSeries3, order = c(3,2,1))

predict(fit, n.ahead = 7)

#[1] 3455.122662 2989.562917 3503.258230 4054.215895 3466.820498 3418.025275
#[7] 3602.858162
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

