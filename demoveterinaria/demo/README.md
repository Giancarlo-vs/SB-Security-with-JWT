
# Animal Central

Animal center is an application that allows users to acquire credits to buy pets or any other kind of animal. In adition,  also allows the user to make requests for adopt pets aviables in the AnimalCentral.



## Run Locally

Inside demoveterinaria>demo>src>main>java>com>co>veterinariagian>demo you will find the Class: **MainPetsApplication.java**, run this class to see the application working.

Make sure you have installed Java SDK 17.


## 1. API Reference to Adopt Pet Option
#### Get all items

```http
  GET /petadoption/findallpetAdoptionrequests
```

#### Get Pet Adoption Request  by Id

```http
  GET /petadoption/getPetAdoptionRequestByid/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### POST Adoption Request  

```http
  POST /petadoption/save/
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` | **Required**. Name of the requester.It must be passed as key-value  inside the body request   |
| `petType`      | `string` | **Required**. Type of Pet that the requester wnats to adopt.It must be passed as key-value  inside the body request |
| `description`      | `string` | **Optional**. Description of your adopt request.It must be passed as key-value  inside the body request |
| `telephone`      | `string` | **Optional**. Number of the requester telephone.(10 DIGITS MAX). It must be pass as key-value  inside the body request |


#### DELETE All Pet Credits by Id

```http
  DELETE /petadoption/deleteAllpetrequests
```







## 2. API Reference to Credit Option


#### Get all items

```http
  GET /petcredit/findallpetcredits
```


#### Get Pet Credit by id

```http
  GET /petcredit/getPetcreditbyid/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |



#### POST Pet Credit 

```http
  POST /petcredit/save/
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `totalAmount`      | `double` | **Required**. Amount requested to post |
| `amountPaid`      | `double` | **Required**. Amount paid to post(initial fee, if you have).It must be passed as key-value  inside the body request |
| `description`      | `string` | **Optional**. Description of your credit request.It must be passed as key-value  inside the body request |

#### PUT Pet Credit by id

```http
  PUT /petcredit/update/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |
| `amountPaid`      | `string` | **Required**. It must be passed as key-value  inside the body request |

#### DELETE Pet Credit by Id

```http
  DELETE /petcredit/deletepetcreditbyid/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### DELETE All Pet Credits

```http
  DELETE /petcredit/deleteAll
```
## 3. API Reference to Actuator Option


#### Get all the operative information of the App.

```http
  GET /actuator
```




## Running Tests

To run tests, you must excecute the Test Class: **PetCreditControllerTest.java**, wich is located into the package Test, in the aplication root, being sure that the Junit configuration was allready configure too. 


