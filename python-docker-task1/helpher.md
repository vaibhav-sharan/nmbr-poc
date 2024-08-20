to buid and start app

docker build -t flask-live-app .
docker run --rm -d -p 5000:5000 flask-live-app

docker ps
docker stop container name