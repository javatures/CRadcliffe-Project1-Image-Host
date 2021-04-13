FROM postgres
ENV POSTRGRES_USER host
ENV POSTGRES_PASSWORD M3m3s
ADD schema.sql /docker-entrypoint-initdb.d
EXPOSE 5432