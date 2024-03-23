I created an API to serve my front-end, which is stored in my 'MyQuests_FrontEnd' repository. This API was developed using Java with Spring, and for the database, I chose PostgreSQL.

The project's structure was divided into sections like Controllers, Repositories, DTOs, Services, and Domains, which helped organize the code effectively.

However, I could have improved the versioning of the code. During development, I sometimes forgot to commit each feature or correction, which made version control less precise.

I initially tried to host this API on AWS, but encountered issues when the front-end (hosted on Vercel) attempted to communicate with it due to the lack of an SSL certificate. 
I plan to study AWS more thoroughly and try deploying this project again in the future.

This project was a great learning experience. I gained valuable insights into concepts like the MVC architecture, emphasizing single responsibility in each class, and database management using Spring. 
For the database, I chose PostgreSQL, which was hosted on the Neon platform, a specialized service for PostgreSQL databases.

I plan to enhance the functionality of my project by implementing features such as task creation and deletion, as well as a login/registration system with email validation directly from the front-end. 
Additionally, I intend to create several routes with GET methods to fulfill specific requirements of the front-end.

Furthermore, I aim to introduce a verification system that sends a code to the user's email for added security.
