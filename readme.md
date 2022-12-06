## XMLParser

This Project is for validate xml data and save the data in the mysql

### System Vesrion 

- Java Vesrion: JDK 17
- Maven
- MySQL

### Addon library used

- liquibase for auto sql execution
- mapstruct for auto data transfer from one java object to another 
- lombok for generate getter and setter

### Project setup steps
```sh
java -jar xmlParser.jar 
```
- OR Download sourcecode and setup in IDE


### API list 

#### Parse & Save XML data
 
- MethodType: Post
- URL: http://localhost:8080/iapps/epaper
- Body: Upload XML in request body with variable name "xmlFile"
- Response: 
```json 
{
    "id": 1,
    "deviceInfo": {
        "screenInfo": {
            "width": 1280,
            "height": 752,
            "dpi": 160
        },
        "appInfo": {
            "newspaperName": "abb"
        }
    },
    "error": false
}
```
![Save API Image](https://github.com/psabuwala/xml-parser/blob/master/Image/saveData.PNG)


#### Find all
- MethodType: Post
- URL: http://localhost:8080/iapps/epaper?pageNo=1&sortColumn=width&sortOrder=DESC&search=abb
- Response: 
```json 
{
    "data": [
        {
            "id": 1,
            "deviceInfo": {
                "screenInfo": {
                    "width": 1280,
                    "height": 752,
                    "dpi": 160
                },
                "appInfo": {
                    "newspaperName": "abb"
                }
            },
            "error": false
        }
    ],
    "totalCount": 1
}
```
Description:
> pageNo is start from 1
> If sortColumn value is null then it will sort based on default value newsPaperName
> Searching is working on "newsPaperName"

![FindAll API Image](https://github.com/psabuwala/xml-parser/blob/master/Image/getAll.PNG)


#### Get by Id
- MethodType: Post
- URL: http://localhost:8080/iapps/epaper?pageNo=1&sortColumn=width&sortOrder=DESC&search=abb
- Response: 
```json 
{
    "id": 1,
    "deviceInfo": {
        "screenInfo": {
            "width": 1280,
            "height": 752,
            "dpi": 160
        },
        "appInfo": {
            "newspaperName": "abb"
        }
    },
    "error": false
}
```
![GetById API Image](https://github.com/psabuwala/xml-parser/blob/master/Image/GetById.PNG)


