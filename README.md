## <p align="center"> CI/CD Jenkins + Cucumber Project</p>
___
Ссылка на проекты [Jenkins](http://149.154.71.152:8082/job/IBS_FullStackQA/job/2024-02/job/FazlyakhmetovDA/)
___
Параметры запуска
- type.browser = chrome/firefox
- version.browser = 108.0/109.0
- "cucumber.filter.tags=(@all)"
- "cucumber.filter.tags=(@jdbc)"
- "cucumber.filter.tags=(@jdbc or @all)"
Команды для запуска тестов удаленно в IDEA(terminal):
`mvn clean test -Dtype.browser=chrome -Dversion.browser=108.0 -D"cucumber.filter.tags=(@jdbc or @all)" -Dselenoid.run=true allure:serve` - 
- `mvn clean test -Dtype.browser=chrome -Dversion.browser=108.0 -D"cucumber.filter.tags=(@jdbc or @all)" -Dselenoid.run=true allure:serve` -

Команды для запуска тестов локально в IDEA(terminal):
- `mvn clean test` -Dselenoid.run=false - без аллюр отчетом
- `mvn clean test` -Dselenoid.run=false allure:serve - с аллюр отчетом
___

Буду рад фидбэку)
