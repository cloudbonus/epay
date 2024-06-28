# Epay

## Overview

This component, on its own, offers little functionality and is required for interacting with the blog. This repository uses the state machine from org.springframework.statemachine:spring-statemachine-core to simulate payment processing.
## Schema

![schema](media/epay-schema.jpg)

## Running the Application

1. **Clone the repository**
    ```console  
    git clone https://github.com/cloudbonus/epay.git 
    ```
2. **Clone the blog repository**
   ```console  
    git clone https://github.com/cloudbonus/blog.git
    ```
3. **Setup**
    - Move content from the util folder to the parent directory.
    - Execute `docker-compose.yaml` to set up Apps, PostgreSQL and Kafka.

## To-Do

- [ ] Refactor the code.
- [ ] Update the database schema.

## Contributing

Feel free to explore the code and make improvements. Contributions are welcome!

## License

This project is released under the [CC0 License](https://choosealicense.com/licenses/cc0-1.0/).
