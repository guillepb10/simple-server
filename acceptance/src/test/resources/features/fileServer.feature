Feature: File server

Scenario: File server
	Given next file "#tempdir/testfiles/hello.txt" with createdDate "#createdDate" and content
	"""
	Hello!
	"""
	When I start a service "#serviceFuture" with file serve "#tempdir/testfiles" on "/statics"
	Then I check that "#serviceFuture" is success
	When I send a "GET /statics/hello.txt" getting "#response"
	Then I check that "#response" is equals to "Hello!"
	And I check that "#response" has status code 200

Scenario: File server cache handle
	Given next file "#tempdir/testfiles/hello.txt" with createdDate "#createdDate" and content
	"""
	Hello!
	"""
	When I start a service "#serviceFuture" with file serve "#tempdir/testfiles" on "/statics"
	Then I check that "#serviceFuture" is success
	When I send a "GET /statics/hello.txt" getting "#response"
	And I check that "#response" contains header "last-modified" equals to date "#createdDate"
	When I send a "GET /statics/hello.txt" with if-modified-since header "#createdDate" getting "#response"
	Then I check that "#response" has status code 304
	When I send a "GET /statics/hello.txt" with if-modified-since header "#createdDate" minus 10 seconds getting "#response"
	Then I check that "#response" has status code 200
	And I check that "#response" is equals to "Hello!"
	When I send a "GET /statics/hello.txt" with empty if-modified-since header getting "#response"
	Then I check that "#response" has status code 200
	And I check that "#response" is equals to "Hello!"
	
Scenario: Can only execute GET method to file server
	When I start a service "#serviceFuture" with file serve "#tempdir/testfiles" on "/statics"
	Then I check that "#serviceFuture" is success
	When I send a "POST /statics/hello.txt" getting "#response"
	Then I check that "#response" has status code 405
	And I check that "#response" is equals to ""
	
Scenario: Not found file
	When I start a service "#serviceFuture" with file serve "#tempdir/testfiles" on "/statics"
	Then I check that "#serviceFuture" is success
	When I send a "GET /statics/hello.txt" getting "#response"
	Then I check that "#response" has status code 404
	And I check that "#response" is equals to ""
	
Scenario: Hidden file
	Given next file "#tempdir/testfiles/.hello.txt" with createdDate "#createdDate" and content
	"""
	Hello!
	"""
	When I start a service "#serviceFuture" with file serve "#tempdir/testfiles" on "/statics"
	Then I check that "#serviceFuture" is success
	When I send a "GET /statics/.hello.txt" getting "#response"
	Then I check that "#response" has status code 404
	And I check that "#response" is equals to ""
	
Scenario: File server with API
	Given next file "#tempdir/testfiles/hello.txt" with createdDate "#createdDate" and content
	"""
	Hello from file!
	"""
	When I start a service "#serviceFuture" with file serve "#tempdir/testfiles" on "/statics" and API "com.simplyti.service.APITest"
	Then I check that "#serviceFuture" is success
	When I send a "GET /statics/hello.txt" getting "#response"
	And I check that "#response" is equals to "Hello from file!"
	When I send a "GET /hello" getting "#response"
	Then I check that "#response" is equals to "Hello!"
	
Scenario: File server not match path
	Given next file "#tempdir/testfiles/hello.txt" with createdDate "#createdDate" and content
	"""
	Hello from file!
	"""
	When I start a service "#serviceFuture" with file serve "#tempdir/testfiles" on "/statics" and API "com.simplyti.service.APITest"
	Then I check that "#serviceFuture" is success
	When I send a "GET /other/hello.txt" getting "#response"
	Then I check that "#response" has status code 404
	And I check that "#response" is equals to ""
	
Scenario: Close connection when required
	Given next file "#tempdir/testfiles/hello.txt" with createdDate "#createdDate" and content
	"""
	Hello!
	"""
	When I start a service "#serviceFuture" with file serve "#tempdir/testfiles" on "/statics"
	Then I check that "#serviceFuture" is success
	When I send an HTTP "1.0" "GET /statics/hello.txt" getting "#response"
	Then I check that "#response" has status code 200
	And I check that "#response" is equals to "Hello!"
