FROM mysql:8

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=librarydb
ENV MYSQL_USER=library
ENV MYSQL_PASSWORD=root

EXPOSE 3306


VOLUME ["/var/lib/mysql"]

CMD ["mysqld"]
