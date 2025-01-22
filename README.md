# **WikiGifty**

WikiGifty is an Android application designed for personalized gift management and social interactions. The app enables users to maintain their profiles, manage both public and private wish lists, and interact with friends, all while leveraging Firebase for secure, real-time data storage and management.

---

## **Features**

### **1. User Authentication**
- **Login & Registration**: Users can register with their email and unique user ID and securely log in using Firebase Authentication.
- **Session Management**: Ensures the user remains logged in until they manually log out.

### **2. Profile Management**
- **Personalized Profile**: 
  - Users can update their profile picture via gallery uploads and reset it to a default image.
  - Displays user information such as name, birth date, and a dynamic birthday countdown.
- **Tags Management**:
  - Users can create custom tags prefixed with `#`.
  - Tags are displayed interactively and can be deleted with a long press.
- **Public Wish List**:
  - Users can add products with links to their public wish list.
  - Public wish list items can be clicked to open the provided link in a browser.

### **3. Friends Management**
- **Friend Requests**:
  - Add friends by entering their unique user ID.
  - Friends are displayed in a scrollable list with their names and birthday countdowns.
- **Friend Profiles**:
  - View detailed profiles of friends, including their public wish lists and tags.
  - Open links directly from a friend's wish list.

### **4. Private Wish List**
- **Features**:
  - Add private wish list items with fields for product name, link, and importance (via star rating).
  - Items are stored securely in Firebase and are only visible to the user.
- **Item Management**:
  - Delete items directly from the list.
  - Click links to open product pages in a browser.
- **Empty State**:
  - Shows a helpful prompt when the private wish list is empty.

### **5. Logout**
- Securely log out, clearing the user's session and returning them to the login screen.

---

## **Technologies Used**

### **Frontend**
- **Kotlin**: Used for app logic and functionality.
- **XML**: Designed UI layouts with a responsive and intuitive user experience.
- **Glide**: Handles image loading and profile picture management.

### **Backend**
- **Firebase Authentication**: For user sign-in, sign-out, and registration.
- **Firebase Realtime Database**: Stores and synchronizes user data in real time.
- **Firebase Storage**: Manages profile image uploads and retrieval.

---

## **App Architecture**
- **Pattern**: Follows a **MVVM (Model-View-ViewModel)** structure.
  - **Model**: Data classes such as `WishItem` and `Friend` manage app data.
  - **View**: XML layouts for activity and fragment interfaces.
  - **ViewModel**: Business logic implemented in Kotlin files.

---

## **How to Run the App**

1. Clone the repository:
   ```bash
   git clone https://github.com/andimsewon/WikiGifty.git
   ```
2. Open the project in **Android Studio**.
3. Configure Firebase:
   - Add `google-services.json` to the `app/` directory.
   - Ensure Firebase Realtime Database and Authentication are enabled.
4. Build and run the app on an emulator or physical device.

---

## **Screenshots**

Include screenshots of:
- Login and registration screens.
- Profile screen with tags and public wish list.
- Friends list and friend profiles.
- Private wish list with item management.

---

## **Future Enhancements**
- Add notification reminders for upcoming friend birthdays.
- Enable sharing of public wish lists with friends.
- Integrate advanced analytics to suggest personalized gift recommendations.
- Expand the UI for multi-language support.

---

## **Contributing**
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature/bug fix.
3. Commit your changes and submit a pull request.

---

## **License**
This project is licensed under the [MIT License](LICENSE).

---

## **Acknowledgments**
- Firebase for providing powerful backend services.
- Glide for efficient image loading.
- Android Studio for an amazing development environment.

Feel free to customize further based on your project name, repository link, and additional screenshots or features!
