CREATE SCHEMA 'art_aws_qa2_study' DEFAULT CHARACTER SET utf8;

CREATE USER 'art_qa2_user'@'localhost' IDENTIFIED BY 'art_qa2_pass';

GRANT SELECT ON art_aws_qa2_study.* TO 'art_qa2_user'@'localhost';
GRANT INSERT ON art_aws_qa2_study.* TO 'art_qa2_user'@'localhost';
GRANT UPDATE ON art_aws_qa2_study.* TO 'art_qa2_user'@'localhost';
GRANT DELETE ON art_aws_qa2_study.* TO 'art_qa2_user'@'localhost';