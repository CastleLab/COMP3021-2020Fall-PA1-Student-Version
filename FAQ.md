## Frequently Asked Questions

### Q1: How to specify program arguments when running main class in Intellij IDEA?

1. Open the Main class file in Intellij IDEA and find the main method.
2. Click the `▶` symbol on the left of main method. 
3. Select the `Edit 'Main.main()'...` in the context menu. This will open a window to create a run configuration.
![Q1-1](artifacts/img/Q1-1.png)
4. In the opened window, set the `Name` of this run configuration and specify program arguments in `Program arguments
` textbox. Arguments are separated by space. After that, click `OK` to save the run configuration.
![Q1-2](artifacts/img/Q1-2.png)
5. In the toolbar, select the run configuration you just created and run it by clicking the button `▶` in the toolbar.
![Q1-3](artifacts/img/Q1-3.png)

More information can be found in [Intellij IDEA documentation](https://www.jetbrains.com/help/idea/run-debug-configuration-application.html). 

### Q2: How to run the sample tests?

1. Open the `Edit Configurations` window as shown in the below image.
![Q2-1](artifacts/img/Q2-1.png)
2. In the opened window, click the `✛` button and select `Junit`.
![Q2-2](artifacts/img/Q2-2.png)
3. On the right side, set the name, select `Test kind` as `All in directory` and then set the directory as `src/test
`. After that click `OK` to save the run configuration.
![Q2-3](artifacts/img/Q2-3.png)
4. Run the created run configuration in the toolbar to run tests. 

More information can be found in [Intellij IDEA documentation](https://www.jetbrains.com/help/idea/run-debug-configuration-application.html). 
