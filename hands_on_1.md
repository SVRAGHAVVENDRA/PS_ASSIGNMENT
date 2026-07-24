
---

# Assignment 1: Hands- On -1
**Scenario:** Mobile Cheque Deposit 

**Student Name:** Suddula Vineeth Raghavendra

---

## 1. SDLC Phase Matching

* **(d) Listing what the feature must do** $\rightarrow$ **Requirements**
* **(b) Drawing the screen layout** $\rightarrow$ **Design**
* **(a) Writing the upload code** $\rightarrow$ **Implementation**
* **(e) Checking a blurry photo is rejected** $\rightarrow$ **Testing**
* **(c) Releasing to customers** $\rightarrow$ **Deployment**
* **(f) Fixing a bug reported after launch** $\rightarrow$ **Maintenance**

---

## 2. Requirements Specification

### Functional Requirements (What the system should do)

1. **Photo Capture & Upload:** The app must let customers take a picture of the front and back of a cheque using their phone camera and submit it for deposit.
2. **Deposit Confirmation:** The app must show a instant confirmation screen with a reference number once the cheque is successfully uploaded.

### Non-Functional Requirements (How well the system works)

1. **Security:** All cheque images and account details must be encrypted while sending over the network to protect customer privacy.
2. **Speed:** The app should process the uploaded photo and give a response back to the user within 3 seconds.

---

## 3. SDLC Model Choice & Justification

**Justification:**

I chose Agile because mobile banking apps need continuous testing and user feedback to get things right, especially with camera integration and cheque processing. Since security rules and user interface needs might adjust as we build, Agile lets us release small working updates quickly instead of waiting until everything is finished. It also allows us to catch bugs early during short development sprints.

---

## 4. Key Team Roles

* **Product Owner:** Defines what the cheque feature needs to do and makes sure it fits the bank's business goals.
* **UI/UX Designer:** Creates clear, easy-to-use screen layouts and guides for customers taking cheque photos.
* **Software Developer:** Writes the actual code to process images, handle inputs, and connect the app to the bank's system.
* **Test Engineer:** Tests the app to make sure edge cases—like blurry photos or incorrect amounts—are handled properly before release.
* **DevOps Engineer:** Manages the deployment process to publish the app safely to app stores without causing downtime.