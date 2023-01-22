FROM node:16-alpine3.16 as dev
WORKDIR /usr/src/app
COPY package.json .
RUN npm install
COPY . .
EXPOSE 3001
CMD ["npm", "start"]