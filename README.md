To make it work you need to just launch the app. Then make requests to 

GET http://localhost:8080/api/v0.1/countries - to see available countries


POST http://localhost:8080/api/v0.1/countries/cities 

request body:
{
	"country": "france"
}
