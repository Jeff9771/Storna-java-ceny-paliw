package szkolenie2022czerwiec

class Car {

    Model model
    Producer producer
    FuelType fuelType
    Double engineVolume
    Double fuelConsumption

    static constraints = {
    }

    public String toString(){
        return "Model: " + model.name + " Producer: " + producer.name + " ID: " + id
    }
}
