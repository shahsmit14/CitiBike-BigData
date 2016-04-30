trip.data <- read.csv("D:/BigData/ProjectDataSet/R_Analysis_data/Weather_Holiday_Data.csv", stringsAsFactors=FALSE)
trip.data
trips_by_day <- trip.data$NumberOfTrips

trips_by_day = as.numeric(trips_by_day)

trips_by_day_timeSeries <- ts(trips_by_day, frequency = 7)
trips_by_day_timeSeries
plot(trips_by_day_timeSeries)


install.packages("tseries")
library(tseries)
adf.test(trips_by_day_timeSeries,alternative="stationary")

adf.test(trips_by_day_timeSeries)

library(forecast)
fit <- ets(trips_by_day_timeSeries)
fc <- forecast(fit)
plot(fc)


y <- msts(trips_by_day_timeSeries, seasonal.periods=c(7,365.25))
fit <- tbats(y)
fc <- forecast(fit)

plot(fc)


y <- ts(trips_by_day_timeSeries, frequency=7)
z <- fourier(ts(trips_by_day_timeSeries, frequency=365.25), K=5)
zf <- fourierf(ts(trips_by_day_timeSeries, frequency=365.25), K=5, h=100)
fit <- auto.arima(y, xreg=cbind(z,holiday))
fc <- forecast(fit, xreg=cbind(zf,holidayf), h=100)
fc
plot(fc)
