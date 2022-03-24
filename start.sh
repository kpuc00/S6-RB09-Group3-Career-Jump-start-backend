if [ "$1" = "local" ]; then
    echo "Starting database and phpmyadmin"
    PYTHONPATH=. uvicorn server.main:app --reload --port 8080

else if [ "$1" = "full" ]; then
            echo "Starting auto-reloading webserver"
            PYTHONPATH=. uvicorn server.main:app --reload --port 8080