# Data Scarping:
install.packages("rjson", repos="http://cran.rstudio.com/")
library(rjson)
require(rjson)

jsonURL = "http://citibikenyc.com/stations/json"

combineddataset = NULL

for (i in 25:100)
{
  json_data = fromJSON(file = jsonURL)
  
  names(json_data)
  json_data$executionTime
  
  names(json_data$stationBeanList[[1]])
  
  executionTime = json_data$executionTime
  
  ids = sapply(json_data$stationBeanList, function(x) x$id)
  availableDocks = sapply(json_data$stationBeanList, function(x) x$availableDocks)
  availableBikes = sapply(json_data$stationBeanList, function(x) x$availableBikes)
  stationData = data.frame(time = executionTime, station_id = ids, availableDocks = availableDocks,availableBikes = availableBikes)

  combineddataset = rbind(combineddataset, stationData)
  
  location = sprintf("/home/smit/Documents/BigData/git/CitiBike-BigData/R_Analysis/Scraping/locationData%d.csv",i)
  
  write.csv(stationData, location, row.names = TRUE, na = "")
  
  Sys.sleep(300)
}

write.csv(combineddataset, "/home/smit/Documents/BigData/git/CitiBike-BigData/R_Analysis/Scraping/AllLocationData.csv", row.names = FALSE, na = "")
