const express = require('express');
const nodemailer = require('nodemailer');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const port = 5000;

app.use(cors());
app.use(bodyParser.json());

const transporter = nodemailer.createTransport({
  service: 'gmail',
  auth: {
    user: 'ilakiyabhuvana002@gmail.com',
    pass: 'Bhuvana@2002',
  },
});

app.post('/signup', (req, res) => {
  const { email, message } = req.body;

  const mailOptions = {
    from: 'ilakiyabhuvana002@gmail.com',
    to: email,
    subject: 'Notification',
    text: message,
  };

  transporter.sendMail(mailOptions, (error, info) => {
    if (error) {
      console.error(error);
      res.status(500).json({ status: 'Error sending email' });
    } else {
      console.log('Email sent: ' + info.response);
      res.status(200).json({ status: 'Email sent successfully' });
    }
  });
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
