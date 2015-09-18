# Native Mobile App Package

## Northwind OData application example
The application source code in this repository along with the [tutorial document](http://hcp.sap.com/developers/TutorialCatalog/android_02_build_android_master_detail_app.html) is intended to illustrate the logon and OData access functions of the SAP Mobile Platform SDK for a native Android application. The starting point of the code was the template app generated in the Android Studio master-detail flow wizard, then modified to implement the capabilities below.

The Northwind application registers with the SAP HANA Cloud Platform mobile services, and displays product data (from the [Northwind OData service](http://services.odata.org/V2/Northwind/Northwind.svc/$metadata)) in a master-detail view (on phone and tablet form factors). 

The project illustrates the following:
 * Creation of a master-detail flow in Android Studio
 * Adding the SAP Mobile Platform SDK to an Android project
 * Code required to customize the MAF Logon component of the SDK
 * Creating an application in SAP HANA Cloud Platform mobile services
 * Building an OData resource path with “orderby” query options
 * Application logic and mobile SDK calls to access an OData service that enforces server-side paging
 * Extracting and storing data in the same variable scheme used by the Android Studio generated code
 * Sub-classing the Android Application class use of the Singleton pattern to store the received data
 * JavaDoc and in-line comments
 * Creation of an app in SAP HANA Cloud Platform mobile services

Prerequisites: 
 * Create your HANA Cloud Platform trial account and enable mobile services [[Details]](http://hcp.sap.com/developers/TutorialCatalog/webapp_01_enable_hcp_mobile_services_trial.html)
 * Download and install the latest SAP Mobile Platform SDK and any patch releases [[Download link]](https://store.sap.com/sap/cpa/ui/resources/store/html/SolutionDetails.html?pid=0000013098&catID=MOB&pcntry=US&sap-language=EN&_cp_id=id-1441300266697-0) 
 * Download and install Android Studio [[Download Link]](https://developer.android.com/sdk/index.html)
 * Complete the Configuring Android Studio for Mobile Development tutorial [[Details]](http://hcp.sap.com/developers/TutorialCatalog/android_o1_configuring_android_studio_mobile_dev.html)

Get the source code:
  * Clone the Git repository: ```git clone https://github.com/SAP/cloud-hana-mobile-app-tutorial```
  * or [download the latest release](https://github.com/SAP/cloud-hana-mobile-app-tutorial/archive/master.zip)

Once the pre-requisites are complete, the steps to create the app in HCPms, add the source code to your project and walkthrough of the main classes is contained in the online tutorial here: [[Tutorial Document]](http://hcp.sap.com/developers/TutorialCatalog/android_02_build_android_master_detail_app.html)

If you are interested in hybrid implementations of the same application, you can view those tutorials at the links below:
 * **Web app ([Part 1](http://hcp.sap.com/developers/TutorialCatalog/webapp_02_build_app_from_scratch_web_ide.html) and [Part 2](http://hcp.sap.com/developers/TutorialCatalog/webapp_04_finishing_touches_mobile_webapp.html))** – *using SAP UI5, SAP Web IDE*
 * **[Hybrid app](http://hcp.sap.com/developers/TutorialCatalog/webapp_03_create_deploy_hybrid_app.html)** – *using SAP UI5, SAP Web IDE, Hybrid App Toolkit, Apache Cordova and the Kapsel SDK*

Abbreviations
 * **HCP** - *SAP HANA Cloud Platform*
 * **HCPms** - *SAP HANA Cloud Platform mobile services*


## Important Disclaimers on Legal Aspects
This document is for informational purposes only. Its content is subject to change without notice, and SAP does not warrant that it is error-free. SAP MAKES NO WARRANTIES, EXPRESS OR IMPLIED, OR OF MERCHANTABILITY, OR FITNESS FOR A PARTICULAR PURPOSE.

## Coding Samples
Any software coding and/or code lines / strings ("Code") included in this documentation are only examples and are not intended to be used in a productive system environment. The Code is only intended to better explain and visualize the syntax and phrasing rules of certain coding. SAP does not warrant the correctness and completeness of the Code given herein, and SAP shall not be liable for errors or damages caused by the usage of the Code, unless damages were caused by SAP intentionally or by SAP's gross negligence.

## Copyright and License
Copyright 2015 SAP SE

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:
http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

