# Sliding Puzzle Android Application

A modern Android sliding puzzle game application with beautiful design and smooth animations, built using Jetpack Compose.

## Features

- **Multiple difficulty levels**: 3x3, 4x4, and 5x5 grids for different challenge levels
- **Multiple images**: 5 beautiful pre-installed puzzle images
- **Best time system**: Automatic saving of best time using DataStore
- **Background music**: Background music with toggle on/off functionality
- **Game statistics**: Time and move count tracking
- **Elegant animations**: Smooth transitions and animations using Compose Animation
- **Responsive design**: Adaptive interface for different screen sizes
- **Splash Screen**: Animated splash screen on startup

## Tech Stack

- **Programming Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Navigation**: Navigation Compose
- **Local Storage**: DataStore Preferences
- **Reactive Programming**: StateFlow, Coroutines
- **State Management**: Compose State Management
- **Audio**: Android MediaPlayer API

## Gameplay

1. **Game Launch**: Animated splash screen with logo
2. **Parameter Selection**: Choose image and difficulty level (3x3, 4x4, 5x5)
3. **Game**: Move tiles by tapping them to complete the full image
4. **Victory**: Victory dialog shows with results upon completion
5. **Best Times**: Best time is automatically saved

## Algorithms and Logic

- **Image Splitting**: Dynamic bitmap splitting into tiles according to difficulty
- **Movement Logic**: Adjacent tile validation with empty space
- **Victory Check**: Comparison of current positions with correct ones
- **Shuffle Algorithm**: Random tile shuffling with solvability guarantee

## System Requirements

- **Android SDK**: 24+
- **Android Studio**: Arctic Fox or newer
- **Kotlin**: 1.9+
- **Java**: 11+

## Setup Instructions

### Repository Cloning

```bash
git clone https://github.com/MarynaRina/sliding_puzzle.git
cd sliding_puzzle
```

### Build and Run

```bash
./gradlew assembleDebug
```

Or simply click "Run" in Android Studio.

## Design Features

- **Material Design 3**: Modern design following latest Google guidelines
- **Gradient Backgrounds**: Beautiful gradient transitions for better visual experience
- **Adaptive Sizing**: Automatic tile size adjustment for different screens
- **Smooth Animations**: Animatable API for creating pleasant transitions
- **Custom Fonts**: Using Rubik font for improved readability

## Audio Features

- Background music with auto-start
- Music toggle on/off functionality
- Automatic cleanup on app close

## Data Storage

- **DataStore Preferences** for best time storage
- Asynchronous saving with Kotlin Coroutines
- Automatic data restoration on startup

## Author

**Maryna Maksymchuk**  
[GitHub](https://github.com/MarynaRina) • [LinkedIn](https://www.linkedin.com/in/maryna-maksymchuk-637082287)
