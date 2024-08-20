to buid and start app

docker build -t flask-docker-compose .
docker run --rm -d -p 5000:5000 flask-docker-compose

docker ps
docker stop container name

OR
Build and run the Docker containers: Execute the following commands in your terminal:
docker-compose up --build