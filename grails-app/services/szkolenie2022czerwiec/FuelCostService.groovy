package szkolenie2022czerwiec

import grails.gorm.transactions.Transactional
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

@Transactional
class FuelCostService {

    static final String FUEL_SERVICE_ADDRESS = "https://www.autocentrum.pl/paliwa/ceny-paliw/";


    def calculateCostByFuelUsage(Double fuelUsage,String fuelType) {
        Document doc = Jsoup.connect(FUEL_SERVICE_ADDRESS).get()
        String priceText95 = doc.select('div.price')[0].text()
        String priceTextd = doc.select('div.price')[2].text()
        Double price95 = Double.parseDouble(priceText95.split(" ")[0].replace(",", "."))
        Double priced = Double.parseDouble(priceTextd.split(" ")[0].replace(",", "."))

       // if(fuelType = bezyna){
         //   return price95
      //  } else return priced
        return priced

    }

    def calculateCostByModel(String model, String fuelType) {
        if (model == "ford") {
            return calculateCostByFuelUsage(7.6, fuelType)
        } else {
            return 0
        }
    }

    Car saveNewCar(String modelName, String producerName,
                   String fuelTypeName, Double fuelConsumption, Double engineVolume) {

        Model model = Model.findOrSaveByName(modelName)
        Producer producer = Producer.findOrSaveByName(producerName)
        FuelType fuelType = FuelType.findOrSaveByName(fuelTypeName)

        return Car.findOrSaveWhere(model: model,
                producer: producer,
                fuelType: fuelType,
                fuelConsumption: fuelConsumption,
                engineVolume: engineVolume)
    }
}
