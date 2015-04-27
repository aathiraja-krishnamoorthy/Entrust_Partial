# PAGE TITLE
login_H3=//h3[contains(string(),"Login")]
dashboard_H3=//h3[contains(string(),"Home")]
userManagement_H3=//h3[contains(text(),"Administrators")]
partners_H3=//h3[contains(text(),"Partners")]
roles_H3=//h3[contains(text(),"Roles")]
notes_H3=//h3[contains(text(),"Notes")]
ercotDbLogs_H3=//h3[contains(text(),"Ercot Database Logs")]
leadListLog_H3=//h3[contains(text(),"Lead Batch Logs")]
uploadLeadList_H3=//h3[contains(text(),"Upload Lead Lists")]
manageLeadList_H3=//h3[contains(text(),"Manage Lead Lists")]
coopLeadList_H3=//h3[contains(text(),"Manage Co-op Lead Lists")]
assignLeadList_H3=//h3[contains(text(),"Assign Lead Lists")]
distributionHistory_H3=//h3[contains(text(),"Distribution History")]
leadListLoad_H3=//h3[contains(text(),"Lead List Load")]
uploadDNCList_H3=//h3[contains(text(),"Upload Do Not Call Lists")]
runDNCList_H3=//h3[contains(text(),"Run Do Not Call Lists")]
decryptSSN_H3=//h3[contains(text(),"Decrypting SSN")]
affinityLeads_H3=//h3[contains(text(),"Affinity Users")]
mlmEnrollments_H3=//h3[contains(text(),"Manage MLM Enrollments")]
mlmSettings_H3=//h3[contains(text(),"Edit MLM Setting")]
militaryProspects_H3=//h3[contains(text(),"Military Prospects")]
myAccount_H3=//h3[contains(text(),"My Account")]
leadsByTDSP_H3=//h3[contains(text(),"Report - Assigned leads with each vendor by TDSP")]
enrollments_H3=//h3[contains(text(),"Enrollments")]
salesSummary_H3=//h3[contains(string(),"Sales Summary")]
equifaxCalls_H3=//h3[contains(text(),"Equifax Calls")]
mlmReport_H3=//h2[contains(text(),"MLM Report")]                #H2
depositReport_H3=//h3[contains(text(),"Deposit Report")]
activityLogs_H3=//h3[contains(text(),"Activity Logs")]
products_H3=//h3[contains(text(),"Products")]
manageTDUPricing_H3=//h3[contains(text(),"TDU Prices")]
manageSME_TDUPricing_H3=//h3[contains(text(),"SME Tdu Prices")]
promoCodes_H3=//h3[contains(text(),"Promo Codes")]
pricingHistory_H3=//h3[contains(text(),"Price History")]
leadManagement_H3=//h3[contains(text(),"Commercial Leads")]
enrollmentsReport_H3=//h3[contains(text(),"Commercial Enrollments")]
smeDailyPrice_H3=//h3[contains(text(),"Download Daily Price")]
smeProspects_H3=//h3[contains(text(),"SME Prospects")]




# Login XPath
username_XPath = //*[@id="login-email"]
password_XPath = //*[@id="login-password"]
button_XPath = //button[@type="submit"]
forgot_XPath = //*[@id="link-reminder-login"]
backToLogin_XPath = //*[@id="link-reminder"]


# Dashboard
submitFeatureRequest=//a[contains(text(),"Submit a feature request")]
contactMailTo=//a[contains(text(),"aim@entrustenergy.com")]


# Administrators
exportToExcel=//a[contains(text(),"Export to Excel")]
exportToCSV=//a[contains(text(),"Export to CSV")]
openSearch=//button[@type="submit"]
advanceSearch=//input[@type="submit"]

firstName_users=//*[@id="UserFirstName"]
lastName_users=//*[@id="UserLastName"]
administratorType_users=//*[@id="UserRoleId_chosen"]
administratorType_users_select=//li[@class="active-result" and contains(string(),"Operations")]
