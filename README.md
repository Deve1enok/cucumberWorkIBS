## <p align="center"> CI/CD Jenkins + Cucumber Project</p>
___
## Запуск проекта удаленно через Jenkins
- Ссылка на проекты [Jenkins](http://149.154.71.152:8082/job/IBS_FullStackQA/job/2024-02/job/FazlyakhmetovDA/)
___
## Доступные параметры запуска проекта
- type.browser = chrome
- type.browser = firefox 
- version.browser = 108.0
- version.browser = 109.0
- "cucumber.filter.tags=(@all)"
- "cucumber.filter.tags=(@jdbc)"
- "cucumber.filter.tags=(@jdbc or @all)"
___

## Команды для запуска тестов УДАЛЕННО в IDEA(terminal):

Запуск в chrome (всех тестов)
- `mvn clean test -Dtype.browser=chrome -Dversion.browser=108.0 -D"cucumber.filter.tags=(@jdbc or @all)" -Dselenoid.run=true allure:serve`
  
Запуск в firefox (всех тестов)
- `mvn clean test -Dtype.browser=firefox -Dversion.browser=108.0 -D"cucumber.filter.tags=(@jdbc or @all)" -Dselenoid.run=true allure:serve`

## Команды для запуска тестов ЛОКАЛЬНО в IDEA(terminal):
- `mvn clean test -Dselenoid.run=false` - без аллюр отчетом
- `mvn clean test -Dselenoid.run=false allure:serve` - с аллюр отчетом
___

Буду рад фидбэку)
