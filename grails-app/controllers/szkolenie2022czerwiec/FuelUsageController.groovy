package szkolenie2022czerwiec

import grails.converters.JSON
import grails.converters.XML
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class FuelUsageController {

    FuelCostService fuelCostService

    def index() {
        render "Litwo ojczyzno moja itp"
    }

    def drivingPrice() {
        render fuelCostService.calculateCostByModel(params.model, params.fuelType)

        // insert into car values ( nextval('hibernate_sequence'), 0, 'VW','Golf'...
    }

    def newCarForm(){
        render view: 'carForm', model: [listOfProducers: Producer.list()]
    }

    def saveNewCar() {
        fuelCostService.saveNewCar(params.model, params.producer, params.fuelTypeMyszjolen, 6.2, 1.6)
        redirect action: 'showAllCars'
    }


    static final String FUEL_SERVICE_ADDRESS = "https://www.autocentrum.pl/paliwa/ceny-paliw/";

    def showAllCars(){

        Document doc = Jsoup.connect(FUEL_SERVICE_ADDRESS).get()
        String priceText95 = doc.select('div.price')[0].text()
        String priceTextd = doc.select('div.price')[2].text()
        Double price95 = Double.parseDouble(priceText95.split(" ")[0].replace(",", "."))
        Double priced = Double.parseDouble(priceTextd.split(" ")[0].replace(",", "."))

        render view: 'showAllCars', model: [carList: Car.list(), Price95:price95 , PriceD: priced]
    }

}
