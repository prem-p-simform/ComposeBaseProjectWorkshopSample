# WorkshopSample

## Build

1. Create Arkana env files at root level using below command
    ```bash
    touch .env .env.dev .env.qa .env.prod
    ```
2. See Arkana declared variables in `.arkana.yml` and add them in env files.
3. Generate Arkana using below command
    ```bash
   ./genArkana dev
    ```
   
## Design system

* The design module serves as the cornerstone of our project's design system, providing a centralized and well-defined source of truth for all UI components. By encapsulating the implementation details of Material3 within this module, we ensure consistency, maintainability, and flexibility across our application.
This approach offers several key advantages:
Simplified Maintenance: Updates to UI components or even a complete overhaul of the underlying UI library can be managed within the design module, minimizing the impact on other parts of the project.

* Enhanced Consistency: All UI elements adhere to a unified design language, ensuring a cohesive user experience throughout the application.
Improved Reusability: Developers can readily access and reuse pre-built components from the design module, reducing redundancy and promoting efficiency.

* By adhering to the principle of using only components provided by the design module, we establish a robust and scalable foundation for our application's user interface. This practice streamlines development, simplifies maintenance, and ensures a consistent and visually appealing user experience.

## Arkana Setup for Managing Keys

#### Types of Arkana Setup

There are two primary methods for setting up Arkana keys in an Android project:

1. **Providing Keys into Code**
2. **Providing Keys into `AndroidManifest`**

By default, the project has three flavors and environments available: `dev`, `qa`, and `production`.

#### 1. Providing Keys into Code

- **Configuration File**: `.arkana.yml` located at the root of the project.
- **Key Declaration**: Keys are declared under `global_secrets`.
- **Environment Files**: You need to create environment-specific files at the root of the project. The name of the key will be same in all the environment files. In this case:
  - `.env`
  - `.env.dev`
  - `.env.qa`
  - `.env.production`

This method works based on Arkana flavors, and you will need to manage keys separately for each flavor.

#### 2. Providing Keys into `AndroidManifest`

- **Configuration File**: `.arkana.yml` located under the `buildSrc` folder.
- **Key Declaration**: Keys can be declared under `environment_secrets` or `global_secrets` depending on the use case.
- **Environment File**: Only one `.env` file needs to be created under the `buildSrc` folder. The name of the key should append environment name. For example while adding `apiKey` in our case:
    - `apiKeyDev`
    - `apiKeyQa`
    - `apiKeyProduction`

This method works based on Arkana environments, making it simpler as you manage a single `.env` file. To add the key as a manifest placeholder:

1. **Manifest Placeholder**: Use the generated key as a manifest placeholder.
2. **`Manifest.kt` File**: Insert the key into the manifest using the `Manifest.kt` file located in the `buildSrc` directory.

#### Generating Arkana Keys

1. **Initial Setup**:
   - If this is your first time, provide executable permission to the `genArkana.sh` script:
     ```sh
     chmod +x genArkana.sh
     ```

2. **Re-generating Keys**:
   - Open the terminal at the root of the project.
   - Run the following command to re-generate Arkana keys:
     ```sh
     ./genArkana.sh FLAVOR_NAME
     ```
   - FLAVOR_NAME: `dev`, `qa`, `production`

This setup ensures secure management of keys across different environments and flavors in your Android project, leveraging Arkana's capabilities.
