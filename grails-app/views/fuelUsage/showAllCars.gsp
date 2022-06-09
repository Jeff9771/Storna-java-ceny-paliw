<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>

<table>
    <g:each in="${carList}" var="car">
        <tr>
            <td>ID: ${car.id} - </td>
            <td> Model: ${car.model.name} - </td>
            <td> Producent: ${car.producer.name} - </td>
            <td> Paliwo: ${car.fuelType.name} - </td>
            <td> Ilośc spalania: ${car.fuelConsumption} - </td>
            <td> Koszt za 100km:
                        =<g:if test="${car.fuelType.id == 45}">
                            <td> ${car.fuelConsumption * Price95 }</td>
                        </g:if>
                        <g:elseif test="${car.fuelType.id == 52}">
                            <td> ${car.fuelConsumption * PriceD }</td>
                        </g:elseif>
                        <g:else>
                            unknown fuel!
                        </g:else></td>

        </tr>
    </g:each>
</table>

</body>
</html>