CREATE USER 'art_user'@'localhost' IDENTIFIED BY 'art_pass';

GRANT SELECT ON art_aws_study.* TO 'art_user'@'localhost';
GRANT INSERT ON art_aws_study.* TO 'art_user'@'localhost';
GRANT UPDATE ON art_aws_study.* TO 'art_user'@'localhost';
GRANT DELETE ON art_aws_study.* TO 'art_user'@'localhost';