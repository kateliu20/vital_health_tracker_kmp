# Vital: Track, Monitor, Regulate
<img src="shared/src/commonMain/composeResources/drawable/vital.png" alt="Vital Logo" width="40%" height="40%">

Vital is a comprehensive app designed to help individuals track and manage their overall health and wellness. While it’s primarily tailored for users who want to monitor their blood pressure—including pregnant women, older adults, and individuals with hypertension—it’s suitable for anyone interested in staying informed about their health.

The app features curated articles from the CDC on blood pressure and general health topics, conveniently displayed on the home screen. Additionally, Vital offers a robust set of tools, including a dashboard for insights, a "medicine cabinet" for tracking medications, and a calendar for managing appointments or journaling health-related events.

## Libraries Used
- **[Compose WebView Multiplatform](https://github.com/KevinnZou/compose-webview-multiplatform)**: Providing basic webview functionalities. Used for tha article cards on the welcome screen.
- **[Koala Plot](https://github.com/KoalaPlot/koalaplot-core)**: A library for data visualization, used for the blood pressure graphing.
- **[Compose Multiplatform Date Time Picker](https://github.com/Chaintech-Network/compose_multiplatform_date_time_picker)**: A Compose-based date-time picker. Used for scheduling appointments.
- **[UUID](https://github.com/benasher44/uuid)**: A library for generating UUIDs. Used for generating unique IDs to add or delete items.
- **[Spotless](https://github.com/diffplug/spotless)**: Code formatting plugin.

## Architecture of the App
![vital_design.png](shared/src/commonMain/composeResources/drawable/vital_design.png)

## Screens
### **Appointments**
Dialog for adding a new appointment, including time using [compose multiplatform date time picker](https://github.com/Chaintech-Network/compose_multiplatform_date_time_picker). Displays a list of appointments with a weekly calendar view and lists appointment items with a weekly calendar header.

### **Blood Pressure**
- Shows a dot graph of blood pressure trends with [koala plot](https://github.com/KoalaPlot/koalaplot-core), and allows users to log new blood pressure readings.

### **Medications**
- Dialog for adding or editing medications with a component for displaying medication details. The overall screen lists all medications.

### **Welcome**
- Adds a navigation button to the dashboard. Has article cards that link to CDC articles using [compose WebView multiplatform](https://github.com/KevinnZou/compose-webview-multiplatform).

### **5. ViewModels**
Contains the logic and state management for the app's features.
- `HealthComponent.kt`: Centralized health-related state management.
