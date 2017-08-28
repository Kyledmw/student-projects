FROM mongo

COPY init.json /init.json
CMD mongoimport --host appliance-db --db appliance-db --collection Appliance --type json --file /init.json --jsonArray