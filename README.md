=========

### Bluementors
Mentor Locator PoC for dedicated to blue users, blue mentors and other blue supporters

### Project artifacts:
- ui: contain the angular ui client
    To start the ui project locally go to web/src/main/angular and run 'npm start'
- web: a monolith backend application
    Just run WebFacadeApplication
- domain-model: blue mentoring domain definition
- zuul-gateway: hosts the zuul gateway app and related configuration
    Zuul configuration will fetch the routs from the eureka service
- eureka-service: hosts just the eureka service
- microservices: web wrapper adding eureka registration.


### features
 1. blue users and blue mentors blue registration
 3. blue users and blue mentors administration by blue admins
 4. blue mentoring profile definition and calendar planing for mentors and trainings
 5. blue mentoring selection and planning
 6. blue donations (1$ donations)



